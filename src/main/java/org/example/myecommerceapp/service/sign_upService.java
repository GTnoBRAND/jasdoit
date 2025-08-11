package org.example.myecommerceapp.service;

import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.repo.sign_upRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class sign_upService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private sign_upRepo  sign_upRepo;

    public Users save(Users sign) {
        if (!sign.getPassword().startsWith("$2a$")) { // only encode if not already encoded
            sign.setPassword(encoder.encode(sign.getPassword()));
        }
        return sign_upRepo.save(sign);
    }




    public Optional<Users> findById(Long id){
        return sign_upRepo.findById(id);
    }
    public void updateUser(Long id, Users sign) {
        Users user = sign_upRepo.findById(id)
                .orElseThrow(()->new RuntimeException("user not found"));
        user.setEmail(sign.getEmail());
        user.setPassword(sign.getPassword());

        sign_upRepo.save(user);
    }

    public Optional<Users> findByEmail(String email) {
        return sign_upRepo.findByEmail(email);
    }
}
