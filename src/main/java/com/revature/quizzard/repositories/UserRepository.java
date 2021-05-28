package com.revature.quizzard.repositories;

import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AppUser> findAllUsers() {
        try {
            return sessionFactory.getCurrentSession()
                                 .createQuery("from AppUser", AppUser.class)
                                 .getResultList();
        } catch (NoResultException e) {
            throw new ResourceNotFoundException();
        }
    }

    public Optional<AppUser> findUserById(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(AppUser.class, id));
    }

    public Optional<AppUser> findUserByEmail(String email) {
        try {
            return Optional.of(
                    sessionFactory.getCurrentSession()
                                  .createQuery("from AppUser au where au.email = :email", AppUser.class)
                                  .setParameter("email", email)
                                  .getSingleResult()
            );
        } catch (NoResultException e) {
            throw new ResourceNotFoundException();
        }
    }

    public boolean isEmailAvailable(String email) {
        return sessionFactory.getCurrentSession()
                             .createQuery("from AppUser au where au.email = :email", AppUser.class)
                             .setParameter("email", email)
                             .stream().count() == 0;
    }

    public Optional<AppUser> findUserByUsername(String username) {
        try {
            return Optional.of(
                    sessionFactory.getCurrentSession()
                            .createQuery("from AppUser au where au.username = :username", AppUser.class)
                            .setParameter("username", username)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            throw new ResourceNotFoundException();
        }
    }

    public boolean isUsernameAvailable(String username) {
        return sessionFactory.getCurrentSession()
                             .createQuery("from AppUser au where au.username = :username", AppUser.class)
                             .setParameter("username", username)
                             .stream().count() == 0;
    }

    public List<AppUser> findUsersByFirstName(String firstName) {
        return sessionFactory.getCurrentSession()
                             .createQuery("from AppUser au where au.firstName = :firstName", AppUser.class)
                             .setParameter("firstName", firstName)
                             .getResultList();
    }

    public List<AppUser> findUsersByLastName(String lastName) {
        return sessionFactory.getCurrentSession()
                             .createQuery("from AppUser au where au.lastName = :lastName", AppUser.class)
                             .setParameter("lastName", lastName)
                             .getResultList();
    }

    public List<AppUser> findUsersByRole(AppUser.Role role) {
        return sessionFactory.getCurrentSession()
                .createQuery("from AppUser au where au.role = :role", AppUser.class)
                .setParameter("role", role)
                .getResultList();
    }

    public Optional<AppUser> findUserByUsernameAndPassword(String username, String password) {
        return Optional.of(
            sessionFactory.getCurrentSession()
                          .createQuery("from AppUser au where au.username = :username and au.password = :password", AppUser.class)
                          .setParameter("username", username)
                          .setParameter("password", password)
                          .getSingleResult()
        );
    }

    public AppUser save(AppUser user) {
        return (AppUser) sessionFactory.getCurrentSession().save(user);
    }

}
