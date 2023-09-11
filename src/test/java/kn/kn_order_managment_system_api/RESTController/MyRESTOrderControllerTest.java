package kn.kn_order_managment_system_api.RESTController;


import com.fasterxml.jackson.databind.ObjectMapper;
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
import kn.kn_order_managment_system_api.services.OrderService;
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
                .customerId(1)
                .submissionDate("09-09-2023")
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(1)
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



        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
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

        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
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
                .customerId(1)
                .submissionDate("2023-09-09")
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(1)
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
                .customerId(1)
                .submissionDate("09-09-2023")
                .build();
        orderDAO.saveOrder(order1);
        OrderDTO retrievedOrder = orderDAO.getOrder(1);
        Order DBOrder = modelMapper.map(retrievedOrder, Order.class);

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice("12").build();
        productDAO.saveProduct(product1);
        ProductDTO retrievedProduct = productDAO.getProduct(1);
        Product DBProduct = modelMapper.map(retrievedProduct, Product.class);

        OrderLineDTO orderLineDTO = OrderLineDTO.builder().orderId(1).productId(1).quantity(10).build();
        orderLineDAO.saveOrderLine(orderLineDTO);
        List<OrderDTO> allOrders = orderDAO.getAllOrdersByProduct(1);


        given(orderService.getAllOrdersByProduct(1)).willReturn(allOrders);

        ResultActions response = mockMvc.perform(get("/api/orders//by-product/{productId}",1));
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
                .customerId(1)
                .submissionDate("09-09-2023")
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(1)
                .submissionDate("09-09-2023")
                .build();

        allOrdersByCustomer.add(order1);
        allOrdersByCustomer.add(order2);



        given(orderService.getAllOrdersByCustomer(1)).willReturn(allOrdersByCustomer);

        ResultActions response = mockMvc.perform(get("/api/orders/by-customer/{customerId}",1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(allOrdersByCustomer.size())));


    }
    @Test
    public void MyRESTOrderController_deleteOrder_ReturnNothing() throws Exception {
        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
                .submissionDate("09-09-2023")
                .build();
        order1.setOrderId(1);

        ResultActions response = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order1)));

        response.andExpect(status().isCreated());

        String createdOrderJson = response.andReturn().getResponse().getContentAsString();
        OrderDTO createdOrder = objectMapper.readValue(createdOrderJson, OrderDTO.class);

        ResultActions deleteResponse = mockMvc.perform(delete
                ("/api/orders/delete/{orderId}",
                        createdOrder.getOrderId()));

        deleteResponse.andExpect(status().isOk());
    }
}
