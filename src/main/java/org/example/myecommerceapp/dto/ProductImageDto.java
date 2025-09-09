package org.example.myecommerceapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Base64;

public class ProductImageDto implements Serializable {

    private Long id;

    @JsonProperty("data")
    private String base64Data;

    public ProductImageDto(Long id, byte[] data) {
        this.id = id;
        this.base64Data = Base64.getEncoder().encodeToString(data);
    }

    public ProductImageDto() {

    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
