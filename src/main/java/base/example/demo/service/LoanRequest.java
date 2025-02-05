package base.example.demo.service;

public class LoanRequest {
    private Long customerId;
    private Long offerId;
    private double loanAmount;  // Сумма кредита
    private double annualInterestRate; // Годовая процентная ставка
    private int loanTermMonths; // Срок кредита в месяцах

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setLoanTermMonths(int loanTermMonths) {
        this.loanTermMonths = loanTermMonths;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getLoanTermMonths() {
        return loanTermMonths;
    }
}

