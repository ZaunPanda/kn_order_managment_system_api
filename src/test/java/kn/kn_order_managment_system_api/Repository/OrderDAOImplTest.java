package kn.kn_order_managment_system_api.Repository;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.Repository.interfaces.CustomerDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderLineDAO;
import kn.kn_order_managment_system_api.Repository.interfaces.ProductDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderDAOImplTest {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderLineDAO orderLineDAO;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    public void CustomerDAO_getAllOrder_ReturnOrders() throws Exception {

        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(customer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();



        orderDAO.saveOrder(order1);
        orderDAO.saveOrder(order2);

        List<OrderDTO> allOrders = orderDAO.getAllOrders();
        Assertions.assertThat(allOrders.size()).isEqualTo(2);
    }
    @Test
    public void OrderDAO_saveOrder_ReturnOrder() throws Exception {

        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);

        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();


        orderDAO.saveOrder(order1);

        OrderDTO retrievedOrder = orderDAO.getOrder(1);
        Assertions.assertThat(retrievedOrder.getOrderId()).isEqualTo(1);
    }
    @Test
    public void OrderDAO_getOrder_ReturnOrder() throws Exception {

        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build();

        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);

        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer customer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(customer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();


        orderDAO.saveOrder(order1);

        OrderDTO retrievedOrder = orderDAO.getOrder(1);
        Assertions.assertThat(retrievedOrder.getOrderId()).isEqualTo(1);
    }

    @Test
    public void OrderDAO_deleteOrder_ReturnNone() throws Exception {

        Customer customer1 = Customer.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("1234567890")
                .build();
        CustomerDTO customerDTO = modelMapper.map(customer1, CustomerDTO.class);
        customerDAO.saveCustomer(customerDTO);
        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Customer DBcustomer = modelMapper.map(retrievedCustomer, Customer.class);

        OrderDTO order1 = OrderDTO.builder()
                .customerId(DBcustomer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();
        orderDAO.saveOrder(order1);

        orderDAO.deleteOrder(1);


        List<OrderDTO> allOrders = orderDAO.getAllOrders();

        Assertions.assertThat(allOrders.size()).isEqualTo(0);
    }







}
