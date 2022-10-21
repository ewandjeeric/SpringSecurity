package com.example.demo.web;

import com.example.demo.bean.RegistrationForm;
import com.example.demo.models.AppUser;
import com.example.demo.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRoleController {
    @Autowired
    private AccountService accservice;

    @PostMapping("/users")
    public ResponseEntity<AppUser> signUp(@RequestBody RegistrationForm data) {

        String username = data.getUsername();
        AppUser u = accservice.findByUsername(username);

        if (u != null)
            throw new RuntimeException("Cet utilisateur existe déjà, essayez avec un autre nom d'utilisateur");

        String password = data.getPassword();
        String repassword = data.getRepassword();

        if (!password.equals(repassword))
            throw new RuntimeException("Les Mots de passe sont different");

        AppUser user = new AppUser();
        user.setPassword(data.getPassword());
        user.setUsername(data.getUsername());

        accservice.saveUser(user);
        return new ResponseEntity<AppUser>(user,HttpStatus.CREATED);

    }



}
