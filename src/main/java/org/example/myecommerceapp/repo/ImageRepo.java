package org.example.myecommerceapp.repo;

import org.example.myecommerceapp.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ProductImage, Long> {
}
