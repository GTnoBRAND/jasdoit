package org.example.myecommerceapp.dto;

public class loginDto {

    private String email;
    private String password;

    public loginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public loginDto() {
        super();
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
}
