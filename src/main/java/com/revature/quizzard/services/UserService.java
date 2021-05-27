package com.revature.quizzard.services;

import java.util.List;
//import com.revature.quizzard.repos.UserDAO;
import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repos.UserRepository;
//import com.revature.quizzard.util.datasource.ConnectionFactory;
import com.revature.quizzard.util.logging.Logger;
import org.h2.engine.User;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
public class UserService {

    private Logger logger = Logger.getLogger();
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public AppUser saveUser(AppUser user) {
        if(!isUserValid(user)){
            throw new InvalidRequestException("Something was wrong");
        }
        return repository.save(user);
    }

    public AppUser getUserById(int id) {
        return repository.findUserById(id);
    }


    public AppUser authenticate(String username, String password) throws AuthenticationException {
        try {
            return repository.findUserByUsernameAndPassword(username, password);
        } catch (Exception e) {
            throw new AuthenticationException();
        }
    }

    public List<AppUser> getAllUsers(AppUser loggedInUser) {
        if(loggedInUser.getRoleNumber() != 1) {
            throw new AuthenticationException();
        }
        return repository.getAllUsers();
    }

    private boolean isUserValid(AppUser user) {
        Predicate<String> isNullOrEmpty = str -> str == null || str.trim().isEmpty();
        BiPredicate<String, Integer> lengthIsInvalid = (str, length) -> str.length() > length;

        if (user == null) return false;
        if (isNullOrEmpty.test(user.getUsername()) || lengthIsInvalid.test(user.getUsername(), 20)) return false;
        if (isNullOrEmpty.test(user.getPassword()) || lengthIsInvalid.test(user.getPassword(), 255)) return false;
        if (isNullOrEmpty.test(user.getEmail()) || lengthIsInvalid.test(user.getEmail(), 255)) return false;
        if (isNullOrEmpty.test(user.getFirstName()) || lengthIsInvalid.test(user.getFirstName(), 25)) return false;
        if (isNullOrEmpty.test(user.getLastName()) || lengthIsInvalid.test(user.getLastName(), 25)) return false;
        return user.getAge() >= 0;
    }

}
