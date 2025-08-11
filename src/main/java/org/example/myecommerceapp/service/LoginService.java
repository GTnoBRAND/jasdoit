package org.example.myecommerceapp.service;

import org.example.myecommerceapp.dto.loginDto;
import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginService {


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private LoginRepo repo;

    @Autowired
    public LoginService(LoginRepo repo) {
        this.repo = repo;
    }


    public Users login(loginDto request){
//        return repo.findByEmailIgnoreCase(request.getEmail())
//                .filter(user->encoder.matches(request.getPassword(), user.getPassword()))
//                .orElse(null);
        Optional<Users> optUser = repo.findByEmailIgnoreCase(request.getEmail());
        if(optUser.isEmpty()){
            System.out.println("user not found"+ request.getEmail());
            return null;
        }
        Users user = optUser.get();
        System.out.println("DB Hash: " + user.getPassword());
        System.out.println("Raw password: " + request.getPassword());
        System.out.println("Matches? " + encoder.matches(request.getPassword(), user.getPassword()));

        if (encoder.matches(request.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

}
