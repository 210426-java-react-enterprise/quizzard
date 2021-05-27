package com.revature.quizzard.services;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.datasource.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao;
    private Connection mockConnection;
    private ConnectionFactory mockConnectionFactory;
    private MockedStatic<ConnectionFactory> staticMockConnectionFactory;

    @Before
    public void setUp() {
        mockUserDao = mock(UserDAO.class);
        mockConnection = mock(Connection.class);
        mockConnectionFactory = mock(ConnectionFactory.class);
        staticMockConnectionFactory = mockStatic(ConnectionFactory.class);

        when(ConnectionFactory.getInstance()).thenReturn(mockConnectionFactory);
        when(mockConnectionFactory.getConnection()).thenReturn(mockConnection);

        sut = new UserService(mockUserDao);

    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;
        staticMockConnectionFactory.close();
        staticMockConnectionFactory = null;
        mockConnectionFactory = null;
        mockConnection = null;

    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword() throws SQLException {

        // Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(true);

        // Act
        sut.register(new AppUser(0, "un", "pw", "email", "fn", "ln", 18));

        // Assert
        verify(mockConnectionFactory, times(1)).getConnection();
        verify(mockUserDao, times(1)).isUsernameAvailable(any(), anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
        verify(mockUserDao, times(1)).save(any(), any());
        verify(mockConnection, times(1)).commit();
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "sdf", "pw", "email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(0)).isEmailAvailable(any(), anyString());
            verify(mockUserDao, times(0)).save(any(), any());
        }


    }

    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new AppUser(0, "un", "pw", "taken-email", "fn", "ln", 18));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockConnectionFactory, times(1)).getConnection();
            verify(mockUserDao, times(1)).isUsernameAvailable(any(), anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
            verify(mockUserDao, times(0)).save(any(), any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        AppUser invalidUser = new AppUser("", "", "", "", "", 30);

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockConnectionFactory, times(0)).getConnection();
        verify(mockUserDao, times(0)).isUsernameAvailable(any(), anyString());
        verify(mockUserDao, times(0)).isEmailAvailable(any(), anyString());
        verify(mockUserDao, times(0)).save(any(), any());


    }


}
