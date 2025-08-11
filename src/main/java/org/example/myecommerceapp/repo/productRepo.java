package org.example.myecommerceapp.repo;

import org.example.myecommerceapp.dto.ProductDto;
import org.example.myecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface productRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    List<ProductDto> findByNameIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);

}
