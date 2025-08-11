package org.example.myecommerceapp.dto;

import java.util.List;

public class ProductDto {

        private Long id;
        private String name;
        private double price;
        private String description;
        private String type;
        private List<ProductImageDto> images;


    public ProductDto(Long id, String name, double price, String description, String type, List<ProductImageDto> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.images = images;
    }

    public ProductDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductImageDto> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDto> images) {
        this.images = images;
    }
}
