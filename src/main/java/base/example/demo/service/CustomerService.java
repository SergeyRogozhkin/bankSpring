package base.example.demo.service;

import base.example.demo.entity.Customer;
import base.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    //CustomerService методы createCustomer, update, delete
    //длеаю метод который возвращает customerDto вместо customer, убира. этот метод из cystomer controller
    //внутри метода хожу в репозиторий получаю customer -> customerDto . метод возвращает Дто
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


}
