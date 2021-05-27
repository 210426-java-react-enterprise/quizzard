package com.revature.quizzard.services;

import com.revature.quizzard.dtos.AppUserDTO;
import com.revature.quizzard.dtos.AppUserList;
import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static int nextId = 6;
    List<AppUser> users;

    {
        users = new ArrayList<>(Arrays.asList(
                new AppUser("wsingleton", "revature", "wsingleton@gmail.com", "Wezley", "Singleton", 33),
                new AppUser("aanderson", "password", "aanderson@gmail.com", "Alice", "Anderson" , 44),
                new AppUser("bbailey", "password", "bbailey@gmail.com", "Bob", "Bailey", 55),
                new AppUser("ccantrell", "password", "ccantrell@gmail.com", "Chris", "Cantrell", 66),
                new AppUser("ddavis", "password", "ddavis@gmail.com", "Daniel", "Davis", 77))
        );
    }


    public List<AppUser> getAllUsers_json() {
        return users;
    }

    public AppUserList getAllUsers_xml() {
        List<AppUserDTO> userList = users.stream().map(AppUserDTO::new).collect(Collectors.toList());
        return new AppUserList(userList);
    }

    public AppUser authenticate_json(String username, String password){
        AppUser usernameUser = users.stream().filter(appUser -> appUser.getUsername().equals(username))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
        AppUser passUser = users.stream().filter(appUser -> appUser.getPassword().equals(password))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
        if(usernameUser.equals(passUser)){
            return usernameUser;
        }
        return null;
    }
    public AppUser getUserById_json(int id) {
        return users.stream()
                .filter(appUser -> appUser.getId() == id)
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }


    public AppUserDTO getUserById_xml(int id) {
        return users.stream()
                .filter(appUser -> appUser.getId() == id)
                .map(AppUserDTO::new)
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public AppUser getUserByEmail(String email) {
        return users.stream()
                .filter(appUser -> appUser.getEmail().equals(email))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public AppUser createNewUser(AppUser newUser) {
        newUser.setId(nextId++);
        users.add(newUser);
        return newUser;
    }

    public AppUser updateUserEmail(int id, String email) {
        AppUser user = users.get(id - 1);
        user.setEmail(email);
        return user;
    }
}
