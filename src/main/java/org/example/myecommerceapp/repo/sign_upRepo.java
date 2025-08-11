package org.example.myecommerceapp.repo;

import org.example.myecommerceapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface sign_upRepo extends JpaRepository<Users, Long> {
    Users findByName(String name);
    Optional<Users> findByEmail(String email);
}
