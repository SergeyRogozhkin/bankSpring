package base.example.demo.controller;

import base.example.demo.entity.Customer;
import base.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CustomerControllerCreateTest {

    @Mock
    //Эта аннотация говорит Mockito о том, что переменная customerRepository должна быть заменена поддельным (mock) объектом — заглушкой.
    // Этот объект симулирует поведение интерфейса или класса, чтобы мы могли тестировать контроллер без обращения к реальной базе данных.
    private CustomerRepository customerRepository;


    @InjectMocks
    //Эта аннотация заставляет Mockito автоматически внедрить все созданные моки (в данном случае customerRepository)
    // в тестируемый класс (customerController).
    //Например, если в классе CustomerController есть такая зависимость:
    private CustomerController customerController;

    @BeforeEach
    //Это метод JUnit, который выполняется перед каждым тестом из данного тестового класса.
    // Это удобно для настройки среды, выполняемой одинаково для всех тестов.
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //Эта строка инициирует все моки, которые вы объявили
        // в классе с помощью @Mock, и внедряет их туда, где стоит @InjectMocks. Без этого вызова моки не будут работать.
    }

    @Test
    public void addCustomer_Test() {
        // Arrange
        Customer customerMock = new Customer();
        customerMock.setName("John Doe");
        customerMock.setEmail("johndoe@example.com");
        customerMock.setPass(3615123456L);
        customerMock.setPhone(89191234567L);

        when(customerRepository.save(customerMock)).thenReturn(customerMock);
        //реальный метод репозитория не вызывается (и база данных не запрашивается). Вместо этого выполнение эмулируется.

        // Act
        ResponseEntity<Customer> response = customerController.addCustomer(customerMock);
        //Это центральная часть теста. Здесь вы тестируете, как метод контроллера addCustomer
        // работает с переданным объектом customerMock. Метод должен вернуть объект ResponseEntity, внутри которого будет объект типа Customer.

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //Проверяется, что метод контроллера вернул статус код 201 CREATED в ответе. Это ожидаемое поведение при корректном добавлении объекта.
        assertEquals(customerMock, response.getBody());
        //Проверяется, что тело ответа (свойство response.getBody()) содержит объект, который мы передали на сохранение (customerMock).
        // Здесь важно, чтобы equals в классе Customer был правильно переопределен (если используются сложные проверки).
    }




}