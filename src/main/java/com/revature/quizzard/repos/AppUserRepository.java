package com.revature.quizzard.repos;

import com.revature.quizzard.models.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppUserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public AppUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<AppUser> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser", AppUser.class).getResultList();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public AppUser findUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AppUser.class, id);
    }

    @Transactional
    public AppUser findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser a where a.email = :email", AppUser.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<AppUser> findUserByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser a where a.last_name = :last_name", AppUser.class)
                .setParameter("last_name", lastName)
                .getResultList();
    }

    @Transactional
    public AppUser save(AppUser newUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
        return newUser;
    }

}