package kn.kn_order_managment_system_api.Repository;

import jakarta.transaction.Transactional;
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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
                .telephone("+37255667744")
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
                .telephone("+37255667744")
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
                .telephone("+37255667744")
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
    public void OrderDAO_getAllOrdersByDate_ReturnOrderByDate() throws Exception {

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
                .customerId(customer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();

        OrderDTO order2 = OrderDTO.builder()
                .customerId(customer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();


        orderDAO.saveOrder(order1);
        orderDAO.saveOrder(order2);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse("2023-09-09");
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        List<OrderDTO> allOrders = orderDAO.getAllOrdersByDate(sqlStartDate);

        Assertions.assertThat(allOrders.size()).isEqualTo(2);
    }
    @Test
    public void OrderDAO_getAllOrdersByProduct_ReturnOrderByProduct() throws Exception {

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
                .customerId(DBcustomer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();
        orderDAO.saveOrder(order1);
        OrderDTO retrievedOrder = orderDAO.getOrder(1);
        Order DBOrder = modelMapper.map(retrievedOrder, Order.class);

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice(12).build();
        productDAO.saveProduct(product1);
        ProductDTO retrievedProduct = productDAO.getProduct(1);
        Product DBProduct = modelMapper.map(retrievedProduct, Product.class);

        OrderLineDTO orderLineDTO = OrderLineDTO.builder().orderId(DBOrder.getCustomerId()).productId(DBProduct.getProductId()).quantity(10).build();
        orderLineDAO.saveOrderLine(orderLineDTO);
        List<OrderDTO> allOrders = orderDAO.getAllOrdersByProduct(DBProduct.getProductId());

        Assertions.assertThat(allOrders.size()).isEqualTo(1);
    }

    @Test
    public void OrderDAO_getAllOrdersByCustomer_ReturnOrderByCustomer() throws Exception {

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
                .customerId(DBcustomer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();
        orderDAO.saveOrder(order1);

        OrderDTO order2 = OrderDTO.builder()
                .customerId(DBcustomer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();
        orderDAO.saveOrder(order2);


        List<OrderDTO> allOrders = orderDAO.getAllOrdersByCustomer(1);

        Assertions.assertThat(allOrders.size()).isEqualTo(2);
    }
    @Test
    public void OrderDAO_deleteOrder_ReturnNone() throws Exception {

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
                .customerId(DBcustomer.getRegistrationCode())
                .submissionDate(LocalDate.now())
                .build();
        orderDAO.saveOrder(order1);

        orderDAO.deleteOrder(1);


        List<OrderDTO> allOrders = orderDAO.getAllOrders();

        Assertions.assertThat(allOrders.size()).isEqualTo(0);
    }







}
