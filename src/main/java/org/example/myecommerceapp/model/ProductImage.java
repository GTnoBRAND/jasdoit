package org.example.myecommerceapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "bytea")
    private byte[] data;


    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public Product getProduct() {
        return product;
    }
}
