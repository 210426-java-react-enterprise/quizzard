package com.revature.quizzard.repos;

import com.revature.quizzard.models.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public AppUser save(AppUser newUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
        return newUser;
    }
}
