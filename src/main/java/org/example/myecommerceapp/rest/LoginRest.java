package org.example.myecommerceapp.rest;

import org.example.myecommerceapp.dto.loginDto;
import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log")
@CrossOrigin(origins = "*")
public class LoginRest {
    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody loginDto loginDto) {
        Users user = service.login(loginDto);
        if(user!= null){
            System.out.println("Login response user: " + user.getId() + " | email: " + user.getEmail());

            return ResponseEntity.ok(user);
        }
        else {
            System.out.println("Login response user: else" + user.getId() + " | email: " + user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
