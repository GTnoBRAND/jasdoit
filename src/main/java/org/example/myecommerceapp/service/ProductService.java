package org.example.myecommerceapp.service;

import org.example.myecommerceapp.dto.ProductDto;
import org.example.myecommerceapp.dto.ProductImageDto;
import org.example.myecommerceapp.model.Product;
import org.example.myecommerceapp.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ProductDto> searchProduct(String keyword) {
        List<Product> products = repo.searchProducts(keyword);

        return products.stream()
                .map(this::convertToDto)
                .toList();
    }

    private ProductDto convertToDto(Product product) {
        List<ProductImageDto> imageDtos = product.getImages().stream()
                .map(img->new ProductImageDto(img.getId(), img.getData()))
                .collect(Collectors.toList());

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getType(),
                product.getUploaded_at(),
                product.getQuantity(),
                imageDtos
        );
    }

}
