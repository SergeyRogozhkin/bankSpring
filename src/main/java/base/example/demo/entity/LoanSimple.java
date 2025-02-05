package base.example.demo.entity;

import base.example.demo.paymentHisorya.PaymentHistory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class LoanSimple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentHistory> paymentHistoryList = new ArrayList<>();

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setPaymentHistoryList(List<PaymentHistory> paymentHistoryList) {
        this.paymentHistoryList = paymentHistoryList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Offer getOffer() {
        return offer;
    }

    public List<PaymentHistory> getPaymentHistoryList() {
        return paymentHistoryList;
    }
}