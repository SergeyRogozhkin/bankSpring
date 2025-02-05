package base.example.demo.entity;

import base.example.demo.paymentHisorya.PaymentHistory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table
//public class Loan {
//
//    //TODO передавать только 2 айдишника кустоер и оффер
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "offer_id", nullable = false)
//    private Offer offer;
//
//    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PaymentHistory> paymentHistoryList = new ArrayList<>();
//
//    //TODO график платежей набор строк это еще таблица с 1 то мени
//
//    private double loanAmount;
//    private double annualInterestRate;
//    private int loanTermMonths;
//    private double monthlyPayment;
//    private double totalInterest;
//    private double totalPayment;
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Offer getOffer() {
//        return offer;
//    }
//
//    public void setOffer(Offer offer) {
//        this.offer = offer;
//    }
//
//    public double getLoanAmount() {
//        return loanAmount;
//    }
//
//    public void setLoanAmount(double loanAmount) {
//        this.loanAmount = loanAmount;
//    }
//
//    public double getAnnualInterestRate() {
//        return annualInterestRate;
//    }
//
//    public void setAnnualInterestRate(double annualInterestRate) {
//        this.annualInterestRate = annualInterestRate;
//    }
//
//    public int getLoanTermMonths() {
//        return loanTermMonths;
//    }
//
//    public void setLoanTermMonths(int loanTermMonths) {
//        this.loanTermMonths = loanTermMonths;
//    }
//
//    public double getMonthlyPayment() {
//        return monthlyPayment;
//    }
//
//    public void setMonthlyPayment(double monthlyPayment) {
//        this.monthlyPayment = monthlyPayment;
//    }
//
//    public double getTotalInterest() {
//        return totalInterest;
//    }
//
//    public void setTotalInterest(double totalInterest) {
//        this.totalInterest = totalInterest;
//    }
//
//    public double getTotalPayment() {
//        return totalPayment;
//    }
//
//    public void setTotalPayment(double totalPayment) {
//        this.totalPayment = totalPayment;
//    }
//}