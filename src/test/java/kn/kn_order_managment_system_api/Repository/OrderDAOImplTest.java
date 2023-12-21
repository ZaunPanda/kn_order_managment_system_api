package kn.kn_order_managment_system_api.Repository;

import kn.kn_order_managment_system_api.Repository.interfaces.CustomerDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderDAOImplTest {

    @Test
    public void CustomerDAO_getAllOrder_saveOrder_ReturnOrders() throws Exception {
        CustomerDAO mockCustomerDAO = mock(CustomerDAO.class);
        OrderDAO mockOrderDAO = mock(OrderDAO.class);

        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build();


        lenient().when(mockCustomerDAO.getCustomer(anyInt())).thenReturn(CustomerDTO.builder()
                .fullName(customer1.getFullName())
                .email(customer1.getEmail())
                .telephone(customer1.getTelephone())
                .build());


        lenient().when(mockOrderDAO.getAllOrders()).thenReturn(Arrays.asList(
                OrderDTO.builder()
                        .customerId(1)
                        .submissionDate(LocalDate.now())
                        .build(),
                OrderDTO.builder()
                        .customerId(1)
                        .submissionDate(LocalDate.now())
                        .build()
        ));


        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
                .submissionDate(LocalDate.now())
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(1)
                .submissionDate(LocalDate.now())
                .build();


        mockCustomerDAO.saveCustomer(any(CustomerDTO.class));
        mockOrderDAO.saveOrder(order1);
        mockOrderDAO.saveOrder(order2);


        List<OrderDTO> allOrders = mockOrderDAO.getAllOrders();
        Assertions.assertThat(allOrders.size()).isEqualTo(2);
    }

    @Test
    public void OrderDAO_deleteOrder_ReturnNone() throws Exception {

        CustomerDAO mockCustomerDAO = mock(CustomerDAO.class);
        OrderDAO mockOrderDAO = mock(OrderDAO.class);


        lenient().when(mockCustomerDAO.getCustomer(anyInt())).thenReturn(CustomerDTO.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build());


        OrderDTO order1 = OrderDTO.builder()
                .customerId(1) // Используем int вместо long
                .submissionDate(LocalDate.now())
                .build();


        lenient().doNothing().when(mockOrderDAO).deleteOrder(0);


        mockCustomerDAO.saveCustomer(any(CustomerDTO.class));
        mockOrderDAO.saveOrder(order1);
        mockOrderDAO.deleteOrder(0);


        OrderDTO orderDeleted = mockOrderDAO.getOrder(0);
        Assertions.assertThat(orderDeleted).isNull();
    }
//






}
