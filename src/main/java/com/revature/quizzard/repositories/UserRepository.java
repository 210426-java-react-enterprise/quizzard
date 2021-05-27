package com.revature.quizzard.repositories;

import com.revature.quizzard.models.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AppUser> findAllUsers() {
        return sessionFactory.getCurrentSession()
                             .createQuery("from AppUser", AppUser.class)
                             .getResultList();
    }

    public AppUser findUserById(int id) {
        return sessionFactory.getCurrentSession().get(AppUser.class, id);
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {
        return sessionFactory.getCurrentSession()
                             .createQuery("from AppUser au where au.username = :username and au.password = :password", AppUser.class)
                             .setParameter("username", username)
                             .setParameter("password", password)
                             .getSingleResult();
    }

}
