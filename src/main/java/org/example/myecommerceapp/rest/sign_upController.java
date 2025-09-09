package org.example.myecommerceapp.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.service.MailSender;
import org.example.myecommerceapp.service.sign_upService;
import org.example.myecommerceapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/sign-new")
public class sign_upController {

    private final JwtUtil jwtUtil;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private sign_upService service;

    public sign_upController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("sign_UP")
    public ResponseEntity<String> signUp(@RequestBody Users user) {
        Optional<Users> existingUser = service.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            Users u = existingUser.get();

            if (u.isVerified()) {
                return ResponseEntity.badRequest().body("You are already registered and verified.");
            }

            // Only send a new token if the user doesn't already have one
            if (u.getVerificationToken() == null) {
                String verificationToken = JwtUtil.generateToken(u.getEmail());
                u.setVerificationToken(verificationToken);
                service.save(u);
                mailSender.sendVerificationEmail(u.getEmail(), verificationToken);
            }

            return ResponseEntity.ok("Verification email has been sent (if not already active).");
        }

        // New user registration
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        String verificationToken = JwtUtil.generateToken(user.getEmail());
        user.setVerificationToken(verificationToken);
        user.setVerified(false);
        service.save(user);
        mailSender.sendVerificationEmail(user.getEmail(), verificationToken);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registration successful. Please check your email to verify your account.");
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
