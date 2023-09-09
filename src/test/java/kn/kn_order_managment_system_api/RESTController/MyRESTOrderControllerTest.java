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
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.services.CustomerService;
import kn.kn_order_managment_system_api.services.OrderService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

}
