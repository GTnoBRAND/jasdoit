package org.example.myecommerceapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "verification_token")
    private String verificationToken;
    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "enabled")
    private boolean enabled;

    private Roles role;

    public boolean isVerified() {
        return isVerified;
    }


    public Users(Long id, String name, String email, String password, String contact_number, String verificationToken, boolean isVerified, String resetToken, boolean enabled, Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact_number = contact_number;
        this.verificationToken = verificationToken;
        this.isVerified = isVerified;
        this.resetToken = resetToken;
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

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", verificationToken='" + verificationToken + '\'' +
                ", isVerified=" + isVerified +
                ", resetToken='" + resetToken + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return isVerified == users.isVerified && enabled == users.enabled && Objects.equals(id, users.id) && Objects.equals(name, users.name) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(contact_number, users.contact_number) && Objects.equals(verificationToken, users.verificationToken) && Objects.equals(resetToken, users.resetToken) && role == users.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, contact_number, verificationToken, isVerified, resetToken, enabled, role);
    }
}
