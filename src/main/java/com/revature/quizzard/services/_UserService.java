package com.revature.quizzard.services;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repos.UserRepository;
import com.revature.quizzard.util.datasource.ConnectionFactory;
import com.revature.quizzard.util.logging.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
public class _UserService {

    private Logger logger = Logger.getLogger();
    private UserDAO userDao;
    private UserRepository userRepository;

    AppUser user = new AppUser("wsingleton", "revature", "wsingleton@gmail.com", "Wezley", "Singleton", 30);


    public _UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public List<AppUser> getAllUsers() {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            return userDao.findAllUsers(conn);
        }  catch (SQLException | DataSourceException e) {
            logger.warn(e.getMessage());
            throw new ResourceNotFoundException();
        }

    }

    public AppUser getUserById(String idStr) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            int id = Integer.parseInt(idStr);

            return userDao.findUserById(conn, id)
                          .orElseThrow(ResourceNotFoundException::new);

        }  catch (SQLException | DataSourceException e) {
            logger.warn(e.getMessage());
            throw new ResourceNotFoundException();
        } catch (NumberFormatException e) {
            throw new InvalidRequestException("An illegal value was provided!");
        }
    }

    public AppUser authenticate(String username, String password) throws AuthenticationException {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            return userDao.findUserByUsernameAndPassword(conn, username, password)
                                      .orElseThrow(AuthenticationException::new);

        } catch (SQLException | DataSourceException e) {
            logger.warn(e.getMessage());
            throw new AuthenticationException();
        }

    }

    public AppUser register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

       userRepository.save(newUser);
        return newUser;

//        if (!isUserValid(newUser)) {
//            throw new InvalidRequestException("Invalid new user data provided!");
//        }
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            if (!userDao.isUsernameAvailable(conn, newUser.getUsername())) {
//                throw new UsernameUnavailableException();
//            }
//
//            if (!userDao.isEmailAvailable(conn, newUser.getEmail())) {
//                throw new EmailUnavailableException();
//            }

//            userDao.save(conn, newUser);
//            conn.commit();
//
//        } catch (SQLException e) {
//            logger.warn(e.getMessage());
//            e.printStackTrace();
//            throw new ResourcePersistenceException();
//        } catch (UsernameUnavailableException | EmailUnavailableException e) {
//            logger.warn(e.getMessage());
//            throw new ResourcePersistenceException(e.getMessage());
//        }
//
//        return "Good Job";

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
