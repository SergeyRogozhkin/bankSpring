package base.example.demo.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    private Double totalAmount; // Общая сумма кредита (с учетом процентов)
    private Double monthlyPayment; // Ежемесячный платёж должно быть количество месяцев
    private Long mouth;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistory;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setPaymentHistory(List<PaymentHistory> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getMouth() {
        return mouth;
    }

    public void setMouth(Long mouth) {
        this.mouth = mouth;
    }

    public Offer getOffer() {
        return offer;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public List<PaymentHistory> getPaymentHistory() {
        return paymentHistory;
    }
}