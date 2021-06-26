package com.revature.quizzard.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
