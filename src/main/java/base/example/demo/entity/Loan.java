package base.example.demo.entity;


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
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    private Double totalAmount; // Общая сумма кредита (с учетом процентов)
    private Double monthlyPayment; // Ежемесячный платёж должно быть количество месяцев


    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistory;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public List<PaymentHistory> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<PaymentHistory> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }
}