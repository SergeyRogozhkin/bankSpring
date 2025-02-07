package base.example.demo.controller;

import base.example.demo.entity.Customer;
import base.example.demo.entity.Loan;
import base.example.demo.entity.Offer;
import base.example.demo.repository.CustomerRepository;
import base.example.demo.repository.LoanRepository;
import base.example.demo.repository.OfferRepository;
import base.example.demo.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/create")
//
//    public ResponseEntity<Loan> createLoan(@RequestParam Long customerId, @RequestParam Long offerId, @RequestParam int termMonths) {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
//        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new RuntimeException("Offer not found"));
//
//        Loan loan = loanService.createLoan(customer, offer, termMonths);
//        loanRepository.save(loan);
//
//       // return ResponseEntity.ok(loan);
//        return new ResponseEntity<>(loan,HttpStatus.CREATED);
//    }

    @PostMapping("/create/{customerId}/{offerId}/{termMonths}")
    public ResponseEntity<Loan> createLoan(
            @PathVariable Long customerId,
            @PathVariable Long offerId,
            @PathVariable int termMonths) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        Loan loan = loanService.createLoan(customer, offer, termMonths);
        loanRepository.save(loan);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }


}
