package com.revature.quizzard.services;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao;

    @Before
    public void setUp() {
        mockUserDao = mock(UserDAO.class);
        sut = new UserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;

    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword() {

        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(true);

        // Act
        sut.register(new AppUser(0, "un", "pw", "email", "fn", "ln", 18));

        // Assert
        verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(anyString());
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "sdf", "pw", "email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(0)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "un", "pw", "taken-email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        AppUser invalidUser = new AppUser("", "", "", "", "", 30);

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockUserDao.isUsernameAvailable(anyString()), times(1));
        verify(mockUserDao.isEmailAvailable(anyString()), times(0));


    }


}

// Stubbing
//class UserDAOStub extends UserDAO {
//    @Override
//    public void save(AppUser newUser) {
//        newUser.setId(1);
//    }
//
//    @Override
//    public boolean isUsernameAvailable(String username) {
//        return true;
//    }
//
//    @Override
//    public boolean isEmailAvailable(String email) {
//        return true;
//    }
//
//    @Override
//    public AppUser findUserByUsernameAndPassword(String username, String password) {
//        AppUser fakeUser = new AppUser(username, password, "fake", "fake", "fake", 18);
//        fakeUser.setId(1);
//        return fakeUser;
//    }
//}
