package com.revature.quizzard.repos;

import com.revature.quizzard.models.AppUser;
import java.util.List;
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

    @Transactional(readOnly = true)
    public AppUser findUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AppUser.class, id);
    }

    @Transactional
    public AppUser save(AppUser appUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(appUser);
        return appUser;
    }

    @Transactional(readOnly = true)
    public AppUser findUserByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM AppUser a where a.username = :username and a.password = :password", AppUser.class)
                .setParameter("username", username).setParameter("password", password).getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<AppUser> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM AppUser", AppUser.class).getResultList();
    }



}
