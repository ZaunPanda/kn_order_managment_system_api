package kn.kn_order_managment_system_api.RESTController;


import com.fasterxml.jackson.databind.ObjectMapper;
import kn.kn_order_managment_system_api.OrderController.RESTOrderController;
import kn.kn_order_managment_system_api.Repository.interfaces.CustomerDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderLineDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.ProductDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import kn.kn_order_managment_system_api.services.interfaces.OrderService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RESTOrderController.class)
public class RESTOrderControllerTest {
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
                .submissionDate(LocalDate.now())
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(1)
                .submissionDate(LocalDate.now())
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
                .submissionDate(LocalDate.now())
                .build();
        order1.setOrderId(1);

        given(orderService.getOrder(1)).willReturn(order1);

        ResultActions response = mockMvc.perform(get("/api/orders/{id}",1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.submissionDate", is(LocalDate.now().toString())));


    }

    @Test
    public void MyRESTOrderController_saveOrder_ReturnOrder() throws Exception{

        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
                .submissionDate(LocalDate.now())
                .build();
        order1.setOrderId(1);

        given(orderService.getOrder(1)).willReturn(order1);

        ResultActions response = mockMvc.perform(get("/api/orders/{id}",1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.submissionDate", is(LocalDate.now().toString())));


    }
    @Test
    public void MyRESTOrderController_deleteOrder_ReturnNothing() throws Exception {
        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
                .submissionDate(LocalDate.now())
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
