package org.example.myecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "This line is required!!")
    @Size(min = 3, max = 20, message = "Min 3 and Max 20 charachters are Allowed!")
    private String name;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid Email!!")
    @Column(unique = true)
    @JsonAlias({"Email, email"})
    private String email;

    @NotBlank(message = "This is required line!!")
    @Size(min = 6, message = "Password must contain at least 6 charachters!!")
    private String password;

    @NotBlank(message = "Contact nuber must be filled!!")
    @Pattern(regexp = "\\+998\\d{9}", message = "Contact number must be a valid 12-digit number!!")
    @Column(name="contact_number",unique = true)
    private String contact_number;


    private Boolean enabled;
    private Roles role;


    public Users(Long id, String name, String email, String password, String contact_number, Boolean enabled, Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact_number = contact_number;
        this.enabled = enabled;
        this.role = role;
    }

    public Users() {
        super();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
