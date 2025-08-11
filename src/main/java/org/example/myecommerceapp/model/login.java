package org.example.myecommerceapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class login {

    @Id
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid Email!!")
    @NotBlank(message = "This must be Filled")
    @Column
    private String email;

    @NotBlank(message = "This line is required!")
    @Size(min = 6, message = "Password should contain 6 characters min!")
    private String password;
}
