package base.example.demo.service;


public class LoanResponse {
    private double monthlyPayment; // Ежемесячный платеж
    private double totalInterest;  // Общая сумма процентов
    private double totalPayment;  // Итоговая сумма выплаты

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
