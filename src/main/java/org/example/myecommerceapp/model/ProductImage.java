package org.example.myecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "product_image")
public class ProductImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "bytea")
    private byte[] data;


    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonBackReference
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
