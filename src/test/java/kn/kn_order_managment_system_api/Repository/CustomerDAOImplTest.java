package kn.kn_order_managment_system_api.Repository;
import kn.kn_order_managment_system_api.Repository.interfaces.CustomerDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CustomerDAOImplTest {

    @Test
    public void CustomerDAO_getAllCustomers_ReturnCustomers() {
        CustomerDAO mockCustomerDAO = mock(CustomerDAO.class);

        when(mockCustomerDAO.getAllCustomers()).thenReturn(Arrays.asList(
                CustomerDTO.builder()
                        .fullName("Customer1")
                        .email("email@email.com")
                        .telephone("1234567890")
                        .build(),
                CustomerDTO.builder()
                        .fullName("Customer2")
                        .email("email@email.com")
                        .telephone("1234567890")
                        .build()
        ));

        List<CustomerDTO> allCustomers = mockCustomerDAO.getAllCustomers();

        Assertions.assertThat(allCustomers).isNotNull();
        Assertions.assertThat(allCustomers.size()).isEqualTo(2);
        verify(mockCustomerDAO, times(1)).getAllCustomers();
        System.out.println(1);
    }
    @Test
    public void CustomerDAO_saveCustomer_getCustomer_ReturnCustomer() {

        CustomerDAO mockCustomerDAO = mock(CustomerDAO.class);

        CustomerDTO customer = CustomerDTO.builder()
                .fullName("CustomerSaveCustomer")
                .email("email@email.com")
                .telephone("1234567890")
                .build();

        mockCustomerDAO.saveCustomer(customer);
        verify(mockCustomerDAO, times(1)).saveCustomer(customer);
        System.out.println(2);
    }

    @Test
    public void CustomerDAO_deleteCustomer_ReturnCustomer() throws Exception {

        CustomerDAO mockCustomerDAO = mock(CustomerDAO.class);

        CustomerDTO customer = CustomerDTO.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build();

        mockCustomerDAO.saveCustomer(customer);
        mockCustomerDAO.deleteCustomer(1);

        CustomerDTO customerDTO = mockCustomerDAO.getCustomer(1);
        Assertions.assertThat(customerDTO).isNull();
        System.out.println(3);

    }



}
