package base.example.demo.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false) //, nullable = false
    private Long id;

    @Column(name = "fio", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "pass", unique = true, nullable = false)
    private Long pass;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "archive")
    private boolean archive;

    public CustomerEntity() {
        created = new Date();
        archive = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPass() {
        return pass;
    }

    public void setPass(Long pass) {
        this.pass = pass;
    }

}
