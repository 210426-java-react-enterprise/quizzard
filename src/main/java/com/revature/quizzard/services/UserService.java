package com.revature.quizzard.services;

import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
@Transactional
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userDao) {
        this.userRepo = userDao;
    }

    public List<AppUser> getAllUsers() {
       return userRepo.findAllUsers();
    }

    public AppUser getUserById(int id) {
        return userRepo.findUserById(id);
    }

    @Transactional(readOnly = true)
    public AppUser authenticate(String username, String password) throws AuthenticationException {
        try {
            return userRepo.findUserByUsernameAndPassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    public void register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

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
