package base.example.demo.mapper;

import base.example.demo.dto.CustomerDto;
import base.example.demo.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomerMapper {
    public CustomerDto toDto(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customerEntity.getName());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setPass(customerEntity.getPass());
        customerDto.setPhone(customerEntity.getPhone());
        return customerDto;
    }

    public CustomerEntity toEntity (CustomerDto customerDto){
        if (customerDto == null) {
            return null;
        }
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPass(customerDto.getPass());
        customerEntity.setPhone(customerDto.getPhone());
        return customerEntity;
    }

    public List<CustomerDto> toDtoList(List<CustomerEntity> entities) {
        return entities.stream()
                .map(this::toDto) // Используется уже существующий метод toDto
                .collect(Collectors.toList());
    }


}
