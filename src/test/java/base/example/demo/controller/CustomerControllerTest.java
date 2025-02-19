package base.example.demo.controller;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;


//
//@WebMvcTest(CustomerController.class)
//public class CustomerControllerTest {
//
//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
//    CustomerService customerService;
//
//@Test
//void showAllCustomers() throws Exception {
//    Mockito.when(this.customerService.getAllCustomers()).thenReturn(createCustomerDtoList());
//
//    mvc.perform(get("/customer/all"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.length()").value(4));
//}









//
//    @Test
//    void findAllShouldReturnAllBooks() throws Exception {
//        Mockito.when(this.bookService.findAll()).thenReturn(getBooks());
//
//        mvc.perform(get("/books"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }

//    private Customer createCustomer(){
//        Customer customer = new Customer();
//        customer.setPhone(89998880011L);
//        customer.setName("Монгол Олег Владимирович");
//        customer.setPass(3617333000L);
//        customer.setEmail("mongol@ya.com");
//        customer.setArchive(false);
//        return customer;
//    }
//
//    private List<CustomerDto> createCustomerDtoList(){
//        List<CustomerDto> customerDtoList = new ArrayList<>();
//        customerDtoList.add(new CustomerDto("Иван Иванович Иванов", "sss@mail.ru" , 89198001022L, 3600123456L));
//        customerDtoList.add(new CustomerDto("Светалана Иванова", "ssFFF@mail.ru" , 89118001011L, 3611123456L));
//        return customerDtoList;
//    }
//
//
//
//}