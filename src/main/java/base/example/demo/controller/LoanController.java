package base.example.demo.controller;

import base.example.demo.entity.CustomerEntity;
import base.example.demo.entity.Loan;
import base.example.demo.entity.Offer;
import base.example.demo.repository.CustomerRepository;
import base.example.demo.repository.LoanRepository;
import base.example.demo.repository.OfferRepository;
import base.example.demo.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final OfferRepository offerRepository;

    public LoanController(
            LoanService loanService,
            LoanRepository loanRepository,
            CustomerRepository customerRepository,
            OfferRepository offerRepository) {
        this.loanService = loanService;
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<Loan> createLoan(
            @RequestParam Long customerId,
            @RequestParam Long offerId,
            @RequestParam int termMonths) {
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Количество месяцев указать > 0.");
        }

        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getArchive()) {
            throw new IllegalStateException("Customer is archived and cannot create a loan.");
        }

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        Loan loan = loanService.createLoan(customer, offer, termMonths);
        loanRepository.save(loan);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }


}
