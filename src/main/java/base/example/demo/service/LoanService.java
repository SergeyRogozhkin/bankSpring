package base.example.demo.service;

import base.example.demo.entity.Customer;
import base.example.demo.entity.Loan;
import base.example.demo.entity.Offer;
import base.example.demo.entity.PaymentHistory;
import base.example.demo.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class LoanService {

   //  @Autowired
   //private LoanRepository loanRepository;
    public Loan createLoan(Customer customer, Offer offer, long loanTermMonths) {
        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setOffer(offer);
        loan.setMouth(loanTermMonths);

        // Рассчёт полной суммы кредита с учётом процентов
        double totalAmount = offer.getSum() + (double) (offer.getSum() * offer.getPercent() * loanTermMonths / 12 / 100);
        loan.setTotalAmount(totalAmount);

        // Рассчёт ежемесячного платежа
        double monthlyPayment = totalAmount / loanTermMonths;
        loan.setMonthlyPayment(monthlyPayment);

//        List<PaymentHistory> paymentSchedule = calculatePaymentSchedule(loan, loanTermMonths, monthlyPayment);
//        loan.setPaymentHistory(paymentSchedule);

      // loanRepository.save(loan);
        return loan;
    }

//    private List<PaymentHistory> calculatePaymentSchedule(Loan loan, long loanTermMonths, double monthlyPayment) {
//        List<PaymentHistory> paymentSchedule = new ArrayList<>();
//        Calendar calendar = Calendar.getInstance();
//        for (int i = 1; i <= loanTermMonths; i++) {
//            PaymentHistory payment = new PaymentHistory();
//            payment.setLoan(loan);
//            payment.setPaymentNumber(i);
//            payment.setAmount(monthlyPayment);
//
//            calendar.add(Calendar.MONTH, 1); // Добавляем месяц к текущей дате
//            payment.setDueDate(calendar.getTime());
//            payment.setIsPaid(false);
//
//            paymentSchedule.add(payment);
//        }
//        return paymentSchedule;
//    }
}