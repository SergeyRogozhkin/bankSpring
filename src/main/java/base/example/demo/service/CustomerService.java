package base.example.demo.service;

import base.example.demo.dto.CustomerDto;
import base.example.demo.entity.CustomerEntity;
import base.example.demo.mapper.CustomerMapper;
import base.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerEntityToDtoMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerEntityToDtoMapper;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return customerMapper.toDto(savedCustomer);
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAllNotActiveCustomers();
        List<CustomerDto> customersDto = customerMapper.toDtoList(customers);
        return customersDto;
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        CustomerEntity customer1 = customer.get();
        if (customer.isEmpty() || customer1.getArchive()) {
            throw new RuntimeException("customer с таким id нет");
        }
        return customerMapper.toDto(customer1);
    }

    //принимать CustomerDto- ентити не нужен
    public CustomerDto updateCustomer(Long id, CustomerEntity customerEntity) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id); //мб нужно вынести логику в CustomerRepository
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("customer с таким id не найден");
        }
        CustomerEntity customerEntity1 = optionalCustomer.get();
        customerEntity1.setName(customerEntity.getName());
        customerEntity1.setEmail(customerEntity.getEmail());
        customerEntity1.setPass(customerEntity.getPass());
        customerEntity1.setPhone(customerEntity.getPhone());
        customerEntity1.setUpdated(new Date());
        CustomerEntity savedCustomer = customerRepository.save(customerEntity1);
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDto deleteCustomer(Long id) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("customer с таким id не найден");
        }
        CustomerEntity existingCustomer = optionalCustomer.get();
        existingCustomer.setArchive(true);
        CustomerEntity deleteCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDto(deleteCustomer);
    }

}
