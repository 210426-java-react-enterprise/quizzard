package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repositories.UserRepository;
import com.revature.quizzard.web.dtos.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userDao) {
        this.userRepo = userDao;
    }

    @Transactional
    public List<AppUser> searchUsers(Map<String, String> requestParamMap) {

        if (requestParamMap.isEmpty()) return getAllUsers();

        List<AppUser> matchingUsers = new ArrayList<>();

        requestParamMap.forEach((key, value) -> {
            switch (key) {
                case "id":
                    matchingUsers.add(getUserById(value));
                    break;
                case "username":
                    matchingUsers.add(getUserByUsername(value));
                    break;
                case "email":
                    matchingUsers.add(getUserByEmail(value));
                    break;
                case "firstName":
                    matchingUsers.addAll(getUsersByFirstName(value));
                    break;
                case "lastName":
                    matchingUsers.addAll(getUsersByLastName(value));
                    break;
                case "role":
                    matchingUsers.addAll(getUsersByRole(value));
            }
        });

        if (matchingUsers.isEmpty())
            throw new ResourceNotFoundException();

        return matchingUsers;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AppUser> getAllUsers() {
       return userRepo.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public AppUser getUserById(String idStr) {
        try {
            return getUserById(Integer.parseInt(idStr));
        } catch (NumberFormatException e) {
            throw new InvalidRequestException("Invalid id value provided!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public AppUser getUserById(int id) {

        if (id <= 0)
            throw new InvalidRequestException("Invalid id value provided!");

        try {
            return userRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public AppUser getUserByUsername(String username) {

        if (!isValid(username, "username"))
            throw new InvalidRequestException("Invalid username value provided!");

        try {
            return userRepo.findAppUserByUsername(username)
                           .orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean isUsernameAvailable(String username) {

        if (!isValid(username, "username"))
            throw new InvalidRequestException("Invalid username value provided!");

        try {
            return userRepo.isUsernameAvailable(username);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }

    }

    @Transactional
    public AppUser getUserByEmail(String email) {

        if (!isValid(email, "email"))
            throw new InvalidRequestException("Invalid email value provided!");

        try {
            return userRepo.findAppUserByEmail(email)
                    .orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean isEmailAvailable(String email) {

        if (!isValid(email, "email"))
            throw new InvalidRequestException("Invalid email value provided!");

        try {
            return userRepo.isEmailAvailable(email);
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AppUser> getUsersByFirstName(String firstName) {

        if (!isValid(firstName, "firstName"))
            throw new InvalidRequestException("Invalid first name value provided!");

        try {
            List<AppUser> users = userRepo.findAppUsersByFirstName(firstName);
            if (users.isEmpty()) throw new ResourceNotFoundException();
            return users;
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AppUser> getUsersByLastName(String lastName) {

        if (!isValid(lastName, "lastName"))
            throw new InvalidRequestException("Invalid last name value provided!");

        try {
            List<AppUser> users = userRepo.findAppUsersByLastName(lastName);
            if (users.isEmpty()) throw new ResourceNotFoundException();
            return users;
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<AppUser> getUsersByRole(String role) {

        if (!isValid(role, "role"))
            throw new InvalidRequestException("Invalid role value provided!");

        try {
            List<AppUser> users = userRepo.findAppUsersByRole(AppUser.Role.valueOf(role));
            if (users.isEmpty()) throw new ResourceNotFoundException();
            return users;
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }


    @Transactional(readOnly = true)
    public Principal authenticate(String username, String password) throws AuthenticationException {

        if (!isValid(username, "username") && !isValid(password, "password"))
            throw new InvalidRequestException("Invalid username value provided!");

        try {

            AppUser authUser = userRepo.findAppUserByUsernameAndPassword(username, password)
                                       .orElseThrow(AuthenticationException::new);

            return new Principal(authUser);

        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) throw e;
            throw new DataSourceException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AppUser register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        isUserValid(newUser);

        if (isUsernameAvailable(newUser.getUsername())) {
            throw new ResourcePersistenceException("Provided username is already taken!");
        }

        if (isEmailAvailable(newUser.getEmail())) {
            throw new ResourcePersistenceException("Provided email is already taken!");
        }

        newUser.setRole(AppUser.Role.BASIC_USER);
        return userRepo.save(newUser);
    }

    public void isUserValid(AppUser u) throws InvalidRequestException {

        if (u == null)
            throw new InvalidRequestException("A null user was provided.");

        if (!isValid(u.getUsername(), "username"))
            throw new InvalidRequestException("An invalid username was provided.");

        if (!isValid(u.getPassword(), "password"))
            throw new InvalidRequestException("An invalid password was provided.");

        if (!isValid(u.getEmail(), "email"))
            throw new InvalidRequestException("An invalid email was provided.");

        if (!isValid(u.getFirstName(), "firstName"))
            throw new InvalidRequestException("An invalid first name was provided.");

        if (!isValid(u.getLastName(), "lastName"))
            throw new InvalidRequestException("An invalid last name was provided.");

    }

    public boolean isValid(String str, String fieldName) {

        if (str == null || str.trim().isEmpty()) return false;

        switch (fieldName) {
            case "username":
                return str.length() <= 20;
            case "firstName":
            case "lastName":
                return str.length() <= 25;
            case "password":
            case "email":
                return str.length() <= 255;
            case "role":
                try {
                    AppUser.Role.valueOf(str);
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            default:
                return false;
        }


    }

}
