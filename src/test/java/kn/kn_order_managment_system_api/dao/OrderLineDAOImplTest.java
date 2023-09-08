package kn.kn_order_managment_system_api.dao;

import jakarta.transaction.Transactional;
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

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderLineDAOImplTest {
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
    public void OrderLineDAO_getAllOrderLines_ReturnAllOrderLines() throws Exception {
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
        OrderLineDTO orderLineDTO2 = OrderLineDTO.builder().orderId(DBOrder).productId(DBProduct).quantity(50).build();

        orderLineDAO.saveOrderLine(orderLineDTO);
        orderLineDAO.saveOrderLine(orderLineDTO2);
        List<OrderLineDTO> allOrders = orderLineDAO.getAllOrderLines();

        Assertions.assertThat(allOrders.size()).isEqualTo(2);
    }

    @Test
    public void OrderLineDAO_saveOrderLine_ReturnSavedOrderLine() throws Exception {
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
        OrderLineDTO orderLine = orderLineDAO.getOrderLine(1);

        Assertions.assertThat(orderLine).isNotNull();
    }

    @Test
    public void OrderLineDAO_getOrderLine_ReturnOrderLine() throws Exception {
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
        OrderLineDTO orderLine = orderLineDAO.getOrderLine(1);

        Assertions.assertThat(orderLine).isNotNull();
    }
    @Test
    public void OrderLineDAO_deleteOrderLine_ReturnOrderLine() throws Exception {
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
        OrderLineDTO orderLine = orderLineDAO.getOrderLine(1);

        orderLineDAO.deleteOrderLine(orderLine.getOrderLineId());

        OrderLineDTO orderLine2 = orderLineDAO.getOrderLine(orderLine.getOrderLineId());

        Assertions.assertThat(orderLine2).isNull();
    }

}
