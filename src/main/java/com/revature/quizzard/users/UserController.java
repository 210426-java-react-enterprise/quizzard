package com.revature.quizzard.users;

import com.revature.quizzard.users.dtos.responses.AppUserResponse;
import com.revature.quizzard.users.dtos.requests.RegistrationRequest;
import com.revature.quizzard.auth.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public List<AppUserResponse> searchUsers(@RequestParam Map<String, String> requestParams, HttpServletResponse resp) {
        List<AppUserResponse> users = userService.searchUsers(requestParams)
                                                 .stream()
                                                 .map(AppUserResponse::new)
                                                 .collect(Collectors.toList());

        resp.setHeader("Cache-Control", "max-age=3600, must-revalidate");
        return users;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AppUserResponse registerNewUser(@RequestBody RegistrationRequest registrationRequest, HttpServletResponse resp) {
        AppUserResponse registeredUser = new AppUserResponse(userService.register(registrationRequest));
        resp.setHeader("Cache-Control", "no-store");
        return registeredUser;
    }

}
