package org.example.myecommerceapp.security;

import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.repo.sign_upRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private sign_upRepo repo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users user = repo.findByName(name);
        if(user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}
