package base.example.demo.service;

import base.example.demo.entity.Customer;
import base.example.demo.entity.Loan;
import base.example.demo.entity.LoanSimple;
import base.example.demo.entity.Offer;
import base.example.demo.paymentHisorya.PaymentHistory;
import base.example.demo.paymentHisorya.Double;
import base.example.demo.repository.CustomerRepository;
import base.example.demo.repository.LoanRepository;
import base.example.demo.repository.LoanSimpleRepository;
import base.example.demo.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanSimpleService {

    private final LoanSimpleRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final OfferRepository offerRepository;

    public LoanSimpleService(LoanRepository loanRepository, CustomerRepository customerRepository, OfferRepository offerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
    }

    public LoanSimple createLoanWithPaymentHistory(Integer customerId, Integer offerId) {
        // Поиск клиента и предложения
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));

        // Создание нового объекта Loan
        LoanSimple loan = new LoanSimple();
        loan.setCustomer(customer);
        loan.setOffer(offer);

        // Генерация PaymentHistory
        List<Double> paymentHistories = createPaymentSchedule(offer.getSum(), offer.getPercent(), loan);
        loan.setPaymentHistoryList(paymentHistories);

        // Сохранение Loan и возвращение результата
        return loanRepository.save(loan);
    }

    private List<Double> createPaymentSchedule(Integer totalAmount, Integer durationMonths, LoanSimple loan) {
        Integer monthlyPayment = 12;
        List<Double> paymentHistories = new ArrayList<>();

        double pay = totalAmount / monthlyPayment;
        for (int i = 0; i < monthlyPayment; i++) {
            paymentHistories.add(pay);

        }

        return paymentHistories;
    }
}
