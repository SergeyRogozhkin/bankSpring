package base.example.demo.controller;

import base.example.demo.dto.CustomerDto;
import base.example.demo.entity.Customer;
import base.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    //TODO ДТо прочитать fastfall подход прочитать
    @Autowired
    private CustomerRepository customerRepository;

    // POST-запрос для добавления нового клиента
    @PostMapping("/create")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    //чтобы без true выводил
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customersDto = customers.stream()
                .filter(customer -> !customer.getArchive()) // Сортируем по булевому полю false
                .map(customer -> new CustomerDto(customer.getName(),// Преобразуем каждый Customer в CustomerDto
                        customer.getEmail(),
                        customer.getPhone(),
                        customer.getPass()))
                .toList();
        return new ResponseEntity<>(customersDto, HttpStatus.OK);
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) {

        CustomerDto customerDto = new CustomerDto();
        Optional<Customer> customer = customerRepository.findById(id);
        Customer customer1 = customer.get();

        if (customer.isEmpty() || customer1.getArchive() == true) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
        customerDto.setName(customer1.getName());
        customerDto.setEmail(customer1.getEmail());
        customerDto.setPhone(customer1.getPhone());
        customerDto.setPass(customer1.getPass());

        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhone(customerDetails.getPhone());
            existingCustomer.setUpdated(new Date());
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setArchive(true);
            Customer deleteCustomer = customerRepository.save(existingCustomer);
            return new ResponseEntity<>(deleteCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
