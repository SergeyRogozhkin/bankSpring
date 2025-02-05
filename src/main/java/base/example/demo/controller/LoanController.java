package base.example.demo.controller;

import base.example.demo.entity.Loan;
import base.example.demo.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {


    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public ResponseEntity<Loan> createLoan(@RequestParam Long customerId, @RequestParam Long offerId) {
        Loan loan = loanService.createLoanWithPaymentHistory(customerId, offerId);

        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }


    /*  @Autowired
    private LoanCalculationService loanCalculationService;

    @PostMapping("/calculate") //передать id клиента. передайть id offera
    //вернуть расчет итоговой суммы по кредиту и график платежей
    public LoanResponse calculateLoan(@RequestBody LoanRequest request) {
        return loanCalculationService.calculateLoan(request);
    }

    */


}
