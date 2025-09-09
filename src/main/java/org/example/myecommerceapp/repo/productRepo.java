package org.example.myecommerceapp.repo;

import org.example.myecommerceapp.dto.ProductDto;
import org.example.myecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface productRepo extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.images " +
    "WHERE  LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%') ) OR "+
    "CAST(p.price AS STRING ) LIKE CONCAT('%', :keyword, '%') OR "+
    "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%') ) OR "+
    "LOWER(p.type) LIKE LOWER(CONCAT('%', :keyword, '%') ) OR "+
    "CAST(p.quantity AS STRING ) LIKE CONCAT('%', :keyword, '%') ")
    List<Product> searchProducts(String keyword);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<ProductDto> filterByPrice(@Param("min") Double min, @Param("max") Double max);

    Optional<Product> findByName(String name);
}
