package base.example.demo.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false) //, nullable = false
    private Long id;

    @Column(name = "sumOfCredit")
    private Long sum;

    @Column(name = "percentOfCredit")
    private Long percent;

    @Column(name = "created")
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

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
