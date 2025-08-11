package org.example.myecommerceapp.repo;

import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.model.login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<Users, Users> {
    Optional<Users> findByEmailIgnoreCase(String email);
}
