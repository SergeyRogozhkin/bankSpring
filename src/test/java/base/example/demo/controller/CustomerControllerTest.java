package base.example.demo.controller;

import base.example.demo.dto.CustomerDto;
import base.example.demo.entity.CustomerEntity;
import base.example.demo.mapper.CustomerMapper;
import base.example.demo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mvc; //объявляем но пока пустая

    @Mock //ктрл q докуменатция
    private CustomerService customerService; //здаем заглушку тк она внутри касмотер контроллер

    @InjectMocks //внедряем заглушку моск в кконтроллер
    private CustomerController customerController;

    private CustomerMapper customerMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();  // создаем собираем мок мвс  на основе объекта контроллер
    }

    //1 тест ХЕппи пас. Проверка пути, как все рабоатет в норме.
    // тест ошибок
    //неправ джейсон, неправильный ендпоинт,
    @Test
    void createCustomer_shouldBeSuccess() throws Exception {
        CustomerDto customer = new CustomerDto();
        customer.setName("Новый клиент");
        customer.setEmail("new@email.ru");
        customer.setPass(1234L);
        customer.setPhone(89198404223L);

        Mockito.when(customerService.addCustomer(ArgumentMatchers.any(CustomerDto.class)))
                .thenReturn(customer);


        mvc.perform(MockMvcRequestBuilders.post("/customer/create")
                        .content("{\"name\": \"Новый клиент\", \"email\": \"new@email.ru\", \"pass\": 1234 , \"phone\": 89198404223}") // JSON тело
                        .contentType(MediaType.APPLICATION_JSON)) // Укажите формат JSON
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.name").value("Новый клиент"))
                .andExpect(jsonPath("$.email").value("new@email.ru"));
    }

    @Test
        //что тестируем_результат теста
    void createCustomer_shouldBeError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer/create")
                        .content("{\"wrong_name\": \"wrong\", \"email\": \"new@email.ru\", \"pass\": 1234 , \"phone\": 89198404223}") // JSON тело
                        .contentType(MediaType.APPLICATION_JSON)) // Укажите формат JSON
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void showAllCustomers() throws Exception {
        Mockito.when(customerService.getAllCustomers()) //поведение метода КОГДА вызывет .алл -> верни метод
                .thenReturn(createCustomerDtoList());

        mvc.perform(MockMvcRequestBuilders.get("/customer/all"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Иван Иванович Иванов")) //jsonPath прочитать!!!!!!!
                .andExpect(jsonPath("$[1].phone").value(89118001011L));
    }

    @Test
    void showCustomerById() throws Exception {
        Mockito.when(customerService.getCustomerById(ArgumentMatchers.anyLong()))
                        .thenReturn(createCustomerDto());

        mvc.perform(MockMvcRequestBuilders.get("/customer/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.name").value("Петров Петр Алексеевич"))
                .andExpect(jsonPath("$.email").value("ppa@mail.ru"))
                .andExpect(jsonPath("$.phone").value(89198001033L))
                .andExpect(jsonPath("$.pass").value(3600123433L));
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDto customerUpdate = new CustomerDto();
        customerUpdate.setName("Новый клиент");
        customerUpdate.setEmail("new@email.ru");
        customerUpdate.setPass(1234L);
        customerUpdate.setPhone(89198404223L);

        Mockito.when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(CustomerEntity.class)))
                .thenReturn(customerUpdate);

        mvc.perform(MockMvcRequestBuilders.put("/customer/{id}", 1L) // Указываем ID 1L
                        .contentType(MediaType.APPLICATION_JSON) // Заголовок для JSON
                        .content("{\"name\": \"Новый клиент\", \"email\": \"new@email.ru\", \"pass\": 1234, \"phone\": 89198404223}") // JSON с данными
                )
                // Проверяем, что статус ответа 200 OK
                .andExpect(status().isOk())
                // Проверяем, что содержимое ответа совпадает с ожидаемым JSON
                .andExpect(content().json("{\"name\": \"Новый клиент\", \"email\": \"new@email.ru\", \"pass\": 1234 , \"phone\": 89198404223}"));

        // Проверяем вызов метода сервиса с нужными параметрами
        Mockito.verify(customerService).updateCustomer(Mockito.eq(1L), Mockito.any(CustomerEntity.class));
    }

    @Test
    void deleteCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Customer Deleted");
        customerDto.setEmail("new@email.ru");
        customerDto.setPass(1234L);

        Mockito.when(customerService.deleteCustomer(Mockito.anyLong()))
                .thenReturn(customerDto);

        mvc.perform(MockMvcRequestBuilders.delete("/customer/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"Customer Deleted\",\"email\":\"new@email.ru\",\"phone\":null,\"pass\":1234}"));

    }

    private CustomerDto createCustomerDto() {
        CustomerDto customerDto = new CustomerDto("Петров Петр Алексеевич", "ppa@mail.ru", 89198001033L, 3600123433L);
        return customerDto;
    }

    private List<CustomerDto> createCustomerDtoList() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerDtoList.add(new CustomerDto("Иван Иванович Иванов", "sss@mail.ru", 89198001022L, 3600123456L));
        customerDtoList.add(new CustomerDto("Светалана Иванова", "ssFFF@mail.ru", 89118001011L, 3611123456L));
        return customerDtoList;
    }
}