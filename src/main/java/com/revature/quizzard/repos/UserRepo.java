package com.revature.quizzard.repos;

import com.revature.quizzard.exceptions.DataSourceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.logging.Logger;

import org.h2.engine.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {
    
    
    private SessionFactory sessionFactory;
    
    
    @Autowired
    public UserRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    @Transactional(readOnly = true)
    public List<AppUser> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser", AppUser.class).getResultList();
    }
    
    
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public AppUser findUserById( int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AppUser.class, id);
    
    }
    
    
    @Transactional
    public AppUser save( AppUser newUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
        return newUser;
    
    }
    
    
    @Transactional
    public boolean isUsernameAvailable(String username) {
        AppUser oneAU = null;
        
        Session session = sessionFactory.getCurrentSession();
        oneAU = session.createQuery("from users u where u.username = :username", AppUser.class)
                      .setParameter("username", username)
                      .getSingleResult();
    
        return oneAU == null;
    }
    
    public boolean isEmailAvailable(String email) {
        AppUser oneAU = null;
    
        Session session = sessionFactory.getCurrentSession();
        oneAU = session.createQuery("from users u where u.email = :email", AppUser.class)
                       .setParameter("email", email)
                       .getSingleResult();
    
        return oneAU == null;
    }
    
    public Optional<AppUser> findUserByUsernameAndPassword(String username, String password) {
        AppUser oneAU = null;
    
        Session session = sessionFactory.getCurrentSession();
        oneAU = session.createQuery("from users u where u.username = :username and u.password =:password", AppUser.class)
                       .setParameter("username", username)
                       .setParameter("password",password)
                       .getSingleResult();
    
        return Optional.ofNullable(oneAU);
    
        
        
    }
    
    
    
}
