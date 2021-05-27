package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    AppUser newUser = new AppUser("wsingleton", "revature", "wsingleton@gmail.com", "Wezley", "Singleton", 30);

    public AppUser register(AppUser user) throws InvalidRequestException, ResourcePersistenceException {

        userRepository.save(newUser);
        return newUser;
    }

    }
