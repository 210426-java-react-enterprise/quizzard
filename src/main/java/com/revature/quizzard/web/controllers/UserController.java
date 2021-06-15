package com.revature.quizzard.web.controllers;

import com.revature.quizzard.exceptions.EmailUnavailableException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.UsernameUnavailableException;
import com.revature.quizzard.web.dtos.AppUserDTO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.dtos.Credentials;
import com.revature.quizzard.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured(allowedRoles = {"Admin"})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AppUserDTO> searchUsers(@RequestParam Map<String, String> requestParams, HttpServletResponse resp) {
        List<AppUserDTO> users = userService.searchUsers(requestParams)
                                            .stream()
                                            .map(AppUserDTO::new)
                                            .collect(Collectors.toList());

        resp.setHeader("Cache-Control", "max-age=3600, must-revalidate");
        return users;
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AppUserDTO registerNewUser(@RequestBody @Valid AppUser newUser, HttpServletResponse resp) {
        AppUserDTO registeredUser = new AppUserDTO(userService.register(newUser));
        resp.setHeader("Cache-Control", "no-store");
        return registeredUser;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(name="/registration",consumes = APPLICATION_JSON_VALUE,produces= APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUserRequest (@Valid @RequestBody AppUser registerRequest, HttpServletResponse resp){
        //TODO: create an encoder for password encryption
        //string userPasswordToEncrpt = registerUserRequest.getPassword();
        //registerRequest.setPassword(encoder(userPasswordToEncrpt)

        Credentials registeredUser = new Credentials();
        registeredUser.setPassword(registerRequest.getPassword());
        registeredUser.setEmail(registerRequest.getEmail());
        try{
            userService.register(registerRequest);
        }catch(UsernameUnavailableException uue){
            return ResponseEntity.badRequest().body("This username has already been used.");
        }catch(EmailUnavailableException eue){
            return ResponseEntity.badRequest().body("This email has already been registered.");
        }catch(InvalidRequestException ire){
            return ResponseEntity.badRequest().body("Invalid information given.");
        }

        Credentials registeredUserCreds = new Credentials();
        registeredUserCreds.setUsername(registeredUser.getUsername());
        registeredUserCreds.setPassword(registeredUser.getPassword());
        return ResponseEntity.ok(userService.authenticate(registeredUserCreds.getUsername(), registeredUserCreds.getPassword()));

    }

}
