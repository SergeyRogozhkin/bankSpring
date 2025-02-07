package base.example.demo.controller;

import base.example.demo.entity.Customer;
import base.example.demo.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Используем WebMvcTest, чтобы тестировать только контроллер
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository; // Подменяем настоящий репозиторий

    @InjectMocks
    private ObjectMapper objectMapper; // Для преобразования объектов в JSON


    @Autowired
    private MockMvc mockMvc; // Для тестирования HTTP-запросов



    // Тестируем метод addCustomer
    @Test
    public void testAddCustomer() throws Exception {
        // Подготовка данных
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");
        customer.setPhone(89198001020L);
        customer.setPass(3617300400L);

        // Заглушка: при вызове save() возвращать созданный объект
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        // Преобразуем объект в JSON
        String customerJson = objectMapper.writeValueAsString(customer);

        // Выполняем POST-запрос на /customer/create
        mockMvc.perform(post("/customer/create")
                        .contentType(MediaType.APPLICATION_JSON) // Указываем, что это JSON-запрос
                        .content(customerJson)) // Передаем тело запроса
                .andExpect(status().isCreated()) // Ожидаем статус 201 CREATED
                .andExpect(jsonPath("$.name").value("John Doe")) // Проверяем, что поле name вернулось правильно
                .andExpect(jsonPath("$.email").value("johndoe@example.com")) // Проверяем email
                .andExpect(jsonPath("$.phone").value("1234567890")); // Проверяем phone

        // Убеждаемся, что метод save был вызван один раз
        verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
    }

    // Тестируем метод getAllCustomers
    @Test
    public void testGetAllCustomers() throws Exception {
        // Подготовка данных
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("johndoe@example.com");
        customer1.setPhone(89198001020L);
        customer1.setPass(3617300400L);
        customer1.setArchive(false);

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("janesmith@example.com");
        customer2.setPhone(89198881122L);
        customer2.setPass(3617333444L);
        customer2.setArchive(true); // Этот клиент не должен быть возвращен

        // Добавляем заглушку на вызов findAll()
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // Выполняем GET-запрос на /customer/all
        mockMvc.perform(get("/customer/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Ожидаем статус 200 OK
                .andExpect(jsonPath("$[0].name").value("John Doe")) // Проверяем, что первый клиент вернулся
                .andExpect(jsonPath("$[0].email").value("johndoe@example.com")) // Email первого клиента
                .andExpect(jsonPath("$[0].phone").value("1234567890")) // Phone первого клиента
                .andExpect(jsonPath("$.length()").value(1)); // Убедились, что вернулся только один клиент (без архивных)

        // Убеждаемся, что метод findAll был вызван один раз
        verify(customerRepository, times(1)).findAll();
    }

    // Тестируем метод getCustomerById
    @Test
    public void testGetCustomerById() throws Exception {
        // Подготовка данных
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");
        customer.setPhone(89198001020L);
        customer.setPass(3617300400L);
        customer.setArchive(false);

        // Заглушка: при вызове findById() возвращаем Optional с клиентом
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Выполняем GET-запрос на /customer/1
        mockMvc.perform(get("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Ожидаем статус 200 OK
                .andExpect(jsonPath("$.name").value("John Doe")) // Проверяем имя
                .andExpect(jsonPath("$.email").value("johndoe@example.com")) // Проверяем email
                .andExpect(jsonPath("$.phone").value("1234567890")); // Проверяем телефон

        // Убеждаемся, что метод findById был вызван
        verify(customerRepository, times(1)).findById(1L);
    }

    // Тестируем метод deleteCustomer
    @Test
    public void testDeleteCustomer() throws Exception {
        // Подготовка данных
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("johndoe@example.com");
        customer.setPhone(89198001020L);
        customer.setPass(3617300400L);
        customer.setArchive(false);

        // Заглушка для findById()
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Выполняем DELETE-запрос на /customer/1
        mockMvc.perform(delete("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Ожидаем статус 200 OK
                .andExpect(jsonPath("$.archive").value(true)); // Убедились, что клиент стал архивным

        // Убеждаемся, что метод findById был вызван
        verify(customerRepository, times(1)).findById(1L);

        // Проверяем, что save() был вызван с измененным клиентом
        verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
    }
}
