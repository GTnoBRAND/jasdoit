package org.example.myecommerceapp.rest;

import org.example.myecommerceapp.dto.ProductImageDto;
import org.example.myecommerceapp.model.Product;
import org.example.myecommerceapp.dto.ProductDto;
import org.example.myecommerceapp.model.ProductImage;
import org.example.myecommerceapp.repo.ImageRepo;
import org.example.myecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(("/api/products"))
@CrossOrigin(origins = "http://localhost:63342")
public class productController {
    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ProductService service;

    @Autowired
    public productController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestPart List<MultipartFile> imageFiles,
                                             @RequestPart Product product) {
        try {
            for (MultipartFile file : imageFiles) {
                ProductImage productImage = new ProductImage();
                productImage.setData(file.getBytes());
                product.addImage(productImage);
                productImage.setProduct(product);
                System.out.println("Quantity from request: " + product.getQuantity());
            }
            service.saveNewProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the product some internal error: " + e.getMessage());
        }
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = service.getAllProducts();

        List<ProductDto> productDtos = products.stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDescription());
            dto.setType(product.getType());

            List<ProductImageDto> imageDtos = product.getImages()
                    .stream().map(image ->
                            new ProductImageDto(image.getId(), image.getData())
                    ).collect(Collectors.toList());

            dto.setImages(imageDtos);
            return dto;
        }).toList();

        return ResponseEntity.ok(productDtos);
    }


    @GetMapping("/get-oneProduct")
    public ResponseEntity<Product> getOneProduct(@RequestParam Long id) {
        return service.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(service.updateProduct(product));
    }

    @GetMapping("/product/{id}/images")
    public ResponseEntity<byte[]> getAllImages(@PathVariable Long id) {

        Optional<ProductImage> image = imageRepo.findById(id);
        if (image.isPresent()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.get().getData());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return service.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam String keyword) {
        List<ProductDto> searchs = service.searchProduct(keyword);
        return ResponseEntity.ok(searchs);
    }
}