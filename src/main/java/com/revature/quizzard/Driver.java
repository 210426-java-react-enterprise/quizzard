package com.revature.quizzard;

import com.revature.quizzard.config.OrmConfig;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.repos.UserRepo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(OrmConfig.class))
        {
            UserRepo studentRepo = container.getBean(UserRepo.class);
        
            List<AppUser> allStudents = studentRepo.findAllUsers();
            System.out.println(allStudents);
        
            AppUser me = studentRepo.findUserById(1);
            System.out.println(me);
        
        }catch(Exception e){
        
            e.printStackTrace();
        }
    }
    
}