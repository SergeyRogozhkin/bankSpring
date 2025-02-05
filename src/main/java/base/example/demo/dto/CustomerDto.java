package base.example.demo.dto;

import jakarta.persistence.Column;

public class CustomerDto {
    private String name;
    private String email;
    private int phone;
    private int pass;

    public CustomerDto(String name, String email, int phone, int pass) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
    }
    public CustomerDto() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public int getPass() {
        return pass;
    }
// сет гет в данном случае не нужны, тк объект передает данные в своих полях
}
