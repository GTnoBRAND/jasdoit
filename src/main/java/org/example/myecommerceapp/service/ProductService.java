package org.example.myecommerceapp.service;

import org.example.myecommerceapp.dto.ProductDto;
import org.example.myecommerceapp.model.Product;
import org.example.myecommerceapp.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private productRepo repo;

    @Autowired
    ProductService(productRepo repo) {
        this.repo=repo;
    }


    @Override
    public Product saveNewProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        Product newProduct = repo.findById(product.getId()).get();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setType(product.getType());
        newProduct.setUploaded_at(product.getUploaded_at());
        newProduct.setImages(product.getImages());
        return newProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<ProductDto> findByNameIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description) {
        return repo.findByNameIgnoreCaseOrDescriptionContainsIgnoreCase(name, description);
    }


}
