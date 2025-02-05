package base.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class LoanCalculationService {

    public LoanResponse calculateLoan(LoanRequest request) {
        double loanAmount = request.getLoanAmount();
        double annualInterestRate = request.getAnnualInterestRate();
        int loanTermMonths = request.getLoanTermMonths();

        double monthlyInterestRate = annualInterestRate / 12 / 100;

        // Рассчитываем ежемесячный платеж по аннуитетной формуле
        double monthlyPayment = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermMonths)) /
                (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1);

        // Общая сумма, уплаченная за весь срок кредита
        double totalPayment = monthlyPayment * loanTermMonths;

        // Общая сумма процентов
        double totalInterest = totalPayment - loanAmount;

        // Возвращаем результат в объекте LoanResponse
        LoanResponse response = new LoanResponse();
        response.setMonthlyPayment(monthlyPayment);
        response.setTotalInterest(totalInterest);
        response.setTotalPayment(totalPayment);

        return response;
    }
}
