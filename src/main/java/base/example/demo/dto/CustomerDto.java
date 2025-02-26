package base.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class CustomerDto {
    @NotNull(message = "Обязательное поле name")
    private String name;
    @NotNull
    private String email;
    @NotNull
    private Long phone;
    @NotNull(message = "Обязательное поле pass")
    private Long pass;

    public CustomerDto(String name, String email, Long phone, Long pass) {
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

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setPass(Long pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhone() {
        return phone;
    }

    public Long getPass() {
        return pass;
    }
}
