package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.*;
import kn.kn_order_managment_system_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/customers")
    public List<CustomerDTO> showAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customer) throws Exception {
        if (customer.getFullName() == null) {
            throw new Exception("Add name of customer");
        }
        customerService.saveCustomer(customer);
        return customer;
    }

    @RequestMapping("/orders")
    public List<OrderDTO> showAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping("/orders-by-date/{text_date}")
    public List<OrderDTO> showAllOrdersByDate(@PathVariable String text_date) throws Exception {
        if (text_date == null) {
            throw new Exception("Add date to link");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(text_date);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return orderService.getAllOrdersByDate(sqlStartDate);
    }
    @RequestMapping("/orders-by-product")
    public List<OrderDTO> showAllOrdersByProduct(@RequestBody Product product) {

        return orderService.getAllOrdersByProduct(product);
    }
    @RequestMapping("/orders-by-customer")
    public List<OrderDTO> showAllOrdersByCustomer(@RequestBody Customer customer) throws Exception {
        if (customer.getRegistrationCode() == 0) {
            throw new Exception("Add valid registartion code");
        }
        return orderService.getAllOrdersByCustomer(customer);
    }

    @PostMapping("/orders")
    public OrderDTO addOrder(@RequestBody OrderDTO order) {
        Timestamp instant = Timestamp.from(Instant.now());
        order.setSubmissionDate(instant.toString());
        orderService.saveOrder(order);
        return order;
    }

    @RequestMapping("/products")
    public List<ProductDTO> showAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO product) {
        productService.saveProduct(product);
        return product;
    }

    @RequestMapping("/order_lines")
    public List<OrderLineDTO> showAllOrderLines() {
        return orderLineService.getAllOrderLines();
    }


}
