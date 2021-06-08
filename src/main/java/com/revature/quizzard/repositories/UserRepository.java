package com.revature.quizzard.repositories;

import com.revature.quizzard.exceptions.ResourceNotFoundException;
import com.revature.quizzard.models.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByEmail(String email);
    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);

    List<AppUser> findAppUsersByFirstName(String firstName);
    List<AppUser> findAppUsersByLastName(String lastName);
    List<AppUser> findAppUsersByRole(AppUser.Role role);


    @Query("select case when count(au) > 0 then true else false end from AppUser au where au.email = :email")
    boolean isEmailAvailable(String email);

    @Query("select case when count(au) > 0 then true else false end from AppUser au where au.username = :username")
    boolean isUsernameAvailable(String username);

}
