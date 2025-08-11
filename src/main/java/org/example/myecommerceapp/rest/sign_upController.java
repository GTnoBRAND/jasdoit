package org.example.myecommerceapp.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.service.sign_upService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/sign-new")
public class sign_upController {



    @Autowired
    private sign_upService service;

    @PostMapping("sign_UP")
    public ResponseEntity<Users> signUp(@RequestBody Users user) {
        Users newUser = service.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> getUser(@PathVariable Long id){
        Optional<Users> user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("csrf");
    }

    @GetMapping("/get-user{UserId}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable Long UserId){
        Optional<Users> user = service.findById(UserId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update-user{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody Users user) {
        service.updateUser(userId, user);
        return ResponseEntity.ok("User credentials updated");
    }
}
