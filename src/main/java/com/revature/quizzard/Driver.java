package com.revature.quizzard;

import com.revature.quizzard.config.AppConfig;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repo.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Driver {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class)) {
            UserRepository userRepo = container.getBean(UserRepository.class);

            List<AppUser> allUsers = userRepo.findAllUsers();
            System.out.println(allUsers);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
