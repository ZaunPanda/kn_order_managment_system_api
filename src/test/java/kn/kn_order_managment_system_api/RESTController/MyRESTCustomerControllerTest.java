package kn.kn_order_managment_system_api.RESTController;


import com.fasterxml.jackson.databind.ObjectMapper;

import kn.kn_order_managment_system_api.RESTcontroller.MyRESTCustomerController;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.services.CustomerService;
import kn.kn_order_managment_system_api.services.OrderLineService;
import kn.kn_order_managment_system_api.services.OrderService;
import kn.kn_order_managment_system_api.services.ProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(MyRESTCustomerController.class)
public class MyRESTCustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void MyRESTController_getAllCustomers_ReturnAllCustomers() throws Exception{
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .email("email@email.com")
                .fullName("TestCustomer")
                .telephone("+37266445533").build();
        customerDTO1.setRegistrationCode(1);
        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .email("email@email.com")
                .fullName("TestCustomer")
                .telephone("+37266445533").build();
        customerDTO2.setRegistrationCode(2);
        customerDTOList.add(customerDTO1);
        customerDTOList.add(customerDTO2);
        given(customerService.getAllCustomers()).willReturn(customerDTOList);

        ResultActions response = mockMvc.perform(get("/api/customers"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(customerDTOList.size())));


    }
    @Test
    public void MyRESTController_getCustomer_ReturnAllCustomers() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .email("email@email.com")
                .fullName("TestCustomer")
                .telephone("+37266445533").build();
        customerDTO1.setRegistrationCode(1);
        given(customerService.getCustomer(customerDTO1.getRegistrationCode()))
                .willReturn(customerDTO1);

        ResultActions response = mockMvc.perform(get("/api/customers/{id}",1));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.fullName", is(customerDTO1.getFullName())));
    }
    @Test
    public void MyRESTController_saveCustomer_ReturnCustomer() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .email("email@email.com")
                .fullName("TestCustomer")
                .telephone("+37266445533").build();
        customerDTO1.setRegistrationCode(1);

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO1)));

        verify(customerService).saveCustomer(customerDTO1);

    }
    @Test
    public void MyRESTController_deleteCustomer_Return200() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .email("email@email.com")
                .fullName("TestCustomer")
                .telephone("+37266445533").build();
        customerDTO1.setRegistrationCode(1);

        ResultActions response = mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO1)));

        response.andExpect(status().isCreated());

        String createdCustomerJson = response.andReturn().getResponse().getContentAsString();
        CustomerDTO createdCustomer = objectMapper.readValue(createdCustomerJson, CustomerDTO.class);

        ResultActions deleteResponse = mockMvc.perform(delete
                ("/api/customers/{id}",
                        createdCustomer.getRegistrationCode()));

        deleteResponse.andExpect(status().isOk());


    }
}
