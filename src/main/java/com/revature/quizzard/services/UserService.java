package com.revature.quizzard.services;
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.exceptions.InvalidRequestException;

public class UserService {
    private UserDAO userDao;

    public UserService(UserDAO userDao){
        this.userDao = userDao;
    }
    public void register(AppUser newUser){
        //valid dates entries upon registration using our own custom exceptions
        if (!isUserValid(newUser)){
            throw new InvalidRequestException("Invalid new user data provided! ");
        }
        if (!userDao.isUsernameAvailible(newUser.getUsername())){
            throw new ResourcePersistenceException("Username taken");
        }
        if (!userDao.isEmailAvailible(newUser.getUsername())){
            throw new ResourcePersistenceException("Email has been used already");
        }
        userDao.save(newUser);
    }

    public boolean isUserValid(AppUser user){
        if (user == null) return false;
        if (user.getUsername()==null|| user.getUsername().trim().isEmpty() || user.getUsername().length() > 20 )  return false;
        if (user.getPassword()==null|| user.getPassword().trim().isEmpty() || user.getPassword().length() > 255 ) return false;
        if (user.getEmail()==null|| user.getEmail().trim().isEmpty() || user.getEmail().length() > 255 ) return false;
        if (user.getFirstName()==null|| user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25 ) return false;
        if (user.getLastName()==null|| user.getLastName().trim().isEmpty() || user.getLastName().length() > 25 ) return false;
        if (user.getAge()<0 )  return false;
     return true;
    }
}
