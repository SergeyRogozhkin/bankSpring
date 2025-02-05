package base.example.demo.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false) //, nullable = false
    private Integer id;

    @Column(name="sumOfCredit")
    private int sum;

    @Column(name="percentOfCredit")
    private int percent;

    @Column(name="created")
    private Date created;

    public Offer() {
        this.created = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getSum() {
        return sum;
    }

    public int getPercent() {
        return percent;
    }
}
