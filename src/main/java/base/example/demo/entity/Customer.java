package base.example.demo.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false) //, nullable = false
    private Integer id;

    @Column(name = "fio")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @Column(name = "pass")
    private int pass;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "archive")
    private boolean archive;

    public Customer() {
        created = new Date();
        archive = false;
    }

    public boolean getArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
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

}
