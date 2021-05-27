package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserRepository {

    private static SessionFactory sessionFactory;

    @Autowired
    public AppUserRepository(SessionFactory sessionFact){
        sessionFactory = sessionFact;
    }

    public static List<AppUser> findAllUsers(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser",AppUser.class).getResultList();
    }

    public static Optional<AppUser> findUserById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser u where u.id = :id",AppUser.class)
                .setParameter("id",id)
                .getResultList()
                .stream()
                .findFirst();
    }

    public static void save(AppUser newUser){
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
    }

    public static boolean isUsernameAvailable(String username){
        Session session = sessionFactory.getCurrentSession();
        try {
            AppUser user = session.createQuery("from AppUser u where u.username = :username", AppUser.class)
                    .setParameter("username", username).getSingleResult();
        }catch(NoResultException e){
            return true;
        }
        return false;
    }

    public static boolean isEmailAvailable(String email) {
        Session session = sessionFactory.getCurrentSession();
        try {
            AppUser user = session.createQuery("from AppUser u where u.username = :username", AppUser.class)
                    .setParameter("username", email).getSingleResult();
        }catch(NoResultException e){
            return true;
        }
        return false;
    }

    public static Optional<AppUser> findUserByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        AppUser user;
        try {
            user = session.createQuery("from AppUser u where  u.username = :username and u.password = :password", AppUser.class)
                    .setParameter("username", username).setParameter("password",password).getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

}

