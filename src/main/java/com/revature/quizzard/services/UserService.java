package com.revature.quizzard.services;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.datasource.ConnectionFactory;
import com.revature.quizzard.util.logging.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class UserService {

    private Logger logger = Logger.getLogger();
    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public AppUser authenticate(String username, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            return userDao.findUserByUsernameAndPassword(conn, username, password)
                          .orElseThrow(AuthenticationException::new);

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new AuthenticationException();
        }

    }

    public void register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            if (!userDao.isUsernameAvailable(conn, newUser.getUsername())) {
                throw new UsernameUnavailableException();
            }

            if (!userDao.isEmailAvailable(conn, newUser.getEmail())) {
                throw new EmailUnavailableException();
            }

            userDao.save(conn, newUser);
            conn.commit();

        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new ResourcePersistenceException();
        }


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
