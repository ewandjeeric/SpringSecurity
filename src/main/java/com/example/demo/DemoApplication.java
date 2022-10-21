package com.example.demo;

import com.example.demo.dao.AppRoleRepository;
import com.example.demo.dao.TaskRepository;
import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import com.example.demo.models.Task;
import com.example.demo.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accs, TaskRepository taskrepo){
        return args -> {
            AppRole role1 = new AppRole(null, "ALL_TASK","Show all task");
            AppRole role2 = new AppRole(null, "CREATE_TASK", "Create task");
            AppRole role3 = new AppRole(null, "DELETE_TASK", "Delete task");
            AppRole role4 = new AppRole(null, "ADD_TO_ROLE_USER", "Add role to user");
            AppUser u1 = new AppUser(null, "admin", "admin");
            AppUser u2 = new AppUser(null, "user", "user");
            AppUser u3 = new AppUser(null, "customer", "custumer");

            accs.saveUser(u1);
            accs.saveUser(u2);
            accs.saveUser(u3);
            accs.saveRole(role1);
            accs.saveRole(role2);
            accs.saveRole(role3);
            accs.saveRole(role4);

            accs.AddRoleUser("admin", "ALL_TASK");
            accs.AddRoleUser("admin", "CREATE_TASK");
            accs.AddRoleUser("admin", "DELETE_TASK");
            accs.AddRoleUser("admin", "ADD_TO_ROLE_USER");
            accs.AddRoleUser("user", "ALL_TASK");
            accs.AddRoleUser("customer", "CREATE_TASK");

            taskrepo.save(new Task(null, "Odinateur", 180000, 125, true));
            taskrepo.save(new Task(null, "Telephone", 150255, 500, true));
            taskrepo.save(new Task(null, "Voiture", 250000, 10, false));


        };
    }

}
