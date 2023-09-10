package kn.kn_order_managment_system_api.RESTController;


import com.fasterxml.jackson.databind.ObjectMapper;
import kn.kn_order_managment_system_api.RESTcontroller.MyRESTCustomerController;
import kn.kn_order_managment_system_api.RESTcontroller.MyRESTOrderController;
import kn.kn_order_managment_system_api.dao.CustomerDAO;
import kn.kn_order_managment_system_api.dao.OrderDAO;
import kn.kn_order_managment_system_api.dao.OrderLineDAO;
import kn.kn_order_managment_system_api.dao.ProductDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import kn.kn_order_managment_system_api.services.CustomerService;
import kn.kn_order_managment_system_api.services.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyRESTOrderController.class)
public class MyRESTOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderDAO orderDAO;
    @MockBean
    private CustomerDAO customerDAO;
    @MockBean
    private ProductDAO productDAO;
    @MockBean
    private OrderLineDAO orderLineDAO;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void MyRESTController_getAllOrders_ReturnAllOrders() throws Exception{
        List<OrderDTO> OrderDTOList = new ArrayList<>();
        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer)
                .submissionDate("09-09-2023")
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(customer)
                .submissionDate("10-09-2023")
                .build();

        OrderDTOList.add(order1);
        OrderDTOList.add(order2);

        given(orderService.getAllOrders()).willReturn(OrderDTOList);

        ResultActions response = mockMvc.perform(get("/api/orders"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(OrderDTOList.size())));


    }

    @Test
    public void MyRESTOrderController_getOrder_ReturnOrder() throws Exception{
        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer)
                .submissionDate("09-09-2023")
                .build();
        order1.setOrderId(1);

        given(orderService.getOrder(1)).willReturn(order1);

        ResultActions response = mockMvc.perform(get("/api/orders/{id}",1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.submissionDate", is("09-09-2023")));


    }

    @Test
    public void MyRESTOrderController_saveOrder_ReturnOrder() throws Exception{
        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer)
                .submissionDate("09-09-2023")
                .build();
        order1.setOrderId(1);

        given(orderService.getOrder(1)).willReturn(order1);

        ResultActions response = mockMvc.perform(get("/api/orders/{id}",1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.submissionDate", is("09-09-2023")));


    }
    @Test
    public void MyRESTOrderController_getAllOrdersByDate_ReturnAllOrdersByDate() throws Exception{
        List<OrderDTO> OrderDTOList = new ArrayList<>();
        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);

        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer)
                .submissionDate("2023-09-09")
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(customer)
                .submissionDate("2023-09-09")
                .build();


        OrderDTOList.add(order1);
        OrderDTOList.add(order2);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse("2023-09-09");
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());



        given(orderService.getAllOrdersByDate(sqlStartDate)).willReturn(OrderDTOList);

        ResultActions response = mockMvc.perform(get("/api/orders/by-date/{text_date}","2023-09-09"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(OrderDTOList.size())));


    }
    @Test
    public void MyRESTOrderController_getAllOrdersByProduct_ReturnAllOrdersByProduct() throws Exception{
        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();
        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer DBcustomer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(DBcustomer)
                .submissionDate("09-09-2023")
                .build();
        orderDAO.saveOrder(order1);
        OrderDTO retrievedOrder = orderDAO.getOrder(1);
        Order DBOrder = modelMapper.map(retrievedOrder, Order.class);

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice("12").build();
        productDAO.saveProduct(product1);
        ProductDTO retrievedProduct = productDAO.getProduct(1);
        Product DBProduct = modelMapper.map(retrievedProduct, Product.class);

        OrderLineDTO orderLineDTO = OrderLineDTO.builder().orderId(DBOrder).productId(DBProduct).quantity(10).build();
        orderLineDAO.saveOrderLine(orderLineDTO);
        List<OrderDTO> allOrders = orderDAO.getAllOrdersByProduct(DBProduct);


        given(orderService.getAllOrdersByProduct(DBProduct)).willReturn(allOrders);

        ResultActions response = mockMvc.perform(get("/api/orders/by-product",DBProduct));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(allOrders.size())));


    }
    @Test
    public void MyRESTOrderController_getAllOrdersByCustomer_ReturnAllOrdersByCustomer() throws Exception{
        List<OrderDTO> allOrdersByCustomer = new ArrayList<>();
        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();
        customer1.setRegistrationCode(1);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer1)
                .submissionDate("09-09-2023")
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(customer1)
                .submissionDate("09-09-2023")
                .build();

        allOrdersByCustomer.add(order1);
        allOrdersByCustomer.add(order2);



        given(orderService.getAllOrdersByCustomer(customer1)).willReturn(allOrdersByCustomer);

        ResultActions response = mockMvc.perform(get("/api/orders/by-customer",customer1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(allOrdersByCustomer.size())));


    }


//    @Test
//    public void MyRESTController_getCustomer_ReturnAllCustomers() throws Exception{
//        CustomerDTO customerDTO1 = CustomerDTO.builder()
//                .email("email@email.com")
//                .fullName("TestCustomer")
//                .telephone("+37266445533").build();
//        customerDTO1.setRegistrationCode(1);
//        given(customerService.getCustomer(customerDTO1.getRegistrationCode()))
//                .willReturn(customerDTO1);
//
//        ResultActions response = mockMvc.perform(get("/api/customers/{id}",1));
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.fullName", is(customerDTO1.getFullName())));
//    }
//    @Test
//    public void MyRESTController_saveCustomer_ReturnCustomer() throws Exception{
//        CustomerDTO customerDTO1 = CustomerDTO.builder()
//                .email("email@email.com")
//                .fullName("TestCustomer")
//                .telephone("+37266445533").build();
//        customerDTO1.setRegistrationCode(1);
//
//        mockMvc.perform(post("/api/customers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(customerDTO1)));
//
//        verify(customerService).saveCustomer(customerDTO1);
//
//    }
//    @Test
//    public void MyRESTController_deleteCustomer_Return200() throws Exception{
//        CustomerDTO customerDTO1 = CustomerDTO.builder()
//                .email("email@email.com")
//                .fullName("TestCustomer")
//                .telephone("+37266445533").build();
//        customerDTO1.setRegistrationCode(1);
//
//        ResultActions response = mockMvc.perform(post("/api/customers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(customerDTO1)));
//
//        response.andExpect(status().isCreated());
//
//        String createdCustomerJson = response.andReturn().getResponse().getContentAsString();
//        CustomerDTO createdCustomer = objectMapper.readValue(createdCustomerJson, CustomerDTO.class);
//
//        ResultActions deleteResponse = mockMvc.perform(delete
//                ("/api/customers/{id}",
//                        createdCustomer.getRegistrationCode()));
//
//        deleteResponse.andExpect(status().isOk());
//
//
//    }
}
