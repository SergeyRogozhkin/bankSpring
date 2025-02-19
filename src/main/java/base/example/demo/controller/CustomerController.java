package base.example.demo.controller;

import base.example.demo.dto.CustomerDto;
import base.example.demo.entity.CustomerEntity;
import base.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // POST-запрос для добавления нового клиента
    @PostMapping("/create")
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customer) {
        final CustomerDto customerSave = customerService.addCustomer(customer);
        return new ResponseEntity<>(customerSave, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    //чтобы без true выводил
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customerDetails) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDetails), HttpStatus.OK);
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }
}
