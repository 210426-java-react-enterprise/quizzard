package com.revature.quizzard.services;

import com.revature.quizzard.daos.UserRepository;
import com.revature.quizzard.exceptions.*;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.datasource.ConnectionFactory;
import com.revature.quizzard.util.logging.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Service
public class UserService {

    private Logger logger = Logger.getLogger();
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAllUsers() {
        try {
            return userRepository.findAllUsers();
        }  catch (DataSourceException e) {
            logger.warn(e.getMessage());
            throw new ResourceNotFoundException();
        }

    }
    /*

    public AppUser getUserById(String idStr) {

        try () {

            int id = Integer.parseInt(idStr);

            return userRepository.findUserById(id)
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

            return userRepository.findUserByUsernameAndPassword(conn, username, password)
                                      .orElseThrow(AuthenticationException::new);

        } catch (SQLException | DataSourceException e) {
            logger.warn(e.getMessage());
            throw new AuthenticationException();
        }

    }
*/
    public AppUser register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }
        try {
            return userRepository.save(newUser);
        } catch (UsernameUnavailableException | EmailUnavailableException e) {
            logger.warn(e.getMessage());
            throw new ResourcePersistenceException(e.getMessage());
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
