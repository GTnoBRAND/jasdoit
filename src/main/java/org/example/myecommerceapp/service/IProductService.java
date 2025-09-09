package org.example.myecommerceapp.service;

import org.example.myecommerceapp.dto.ProductDto;
import org.example.myecommerceapp.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product saveNewProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
