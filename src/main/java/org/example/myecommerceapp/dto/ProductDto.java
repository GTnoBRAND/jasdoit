package org.example.myecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductDto implements Serializable {

        private Long id;
        private String name;
        private Double price;
        private String description;
        private String type;
        private LocalDateTime uploadedAt;
        private Long quantity;
        private List<ProductImageDto> images;


    public ProductDto() {
        super();
    }

    public ProductDto(Long id, String name, Double price, String description, String type, LocalDateTime uploadedAt, Long quantity, List<ProductImageDto> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.uploadedAt = uploadedAt;
        this.quantity = quantity;
        this.images = images;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public LocalDateTime getUploaded_at() {
        return uploadedAt;
    }

    public void setUploaded_at(LocalDateTime uploaded_at) {
        this.uploadedAt = uploaded_at;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", uploadedAt=" + uploadedAt +
                ", quantity=" + quantity +
                ", images=" + images +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(type, that.type) && Objects.equals(uploadedAt, that.uploadedAt) && Objects.equals(quantity, that.quantity) && Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, type, uploadedAt, quantity, images);
    }
}
