package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Product;
import kn.kn_order_managment_system_api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTOrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<OrderDTO> showAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping("/orders/{id}")
    public OrderDTO getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/orders")
    public OrderDTO saveOrder(@RequestBody OrderDTO order) {
        Timestamp instant = Timestamp.from(Instant.now());
        order.setSubmissionDate(instant.toString());
        orderService.saveOrder(order);
        return order;
    }
    @RequestMapping("/orders/by-date/{text_date}")
    public List<OrderDTO> getAllOrdersByDate(@PathVariable String text_date) throws Exception {
        if (text_date == null) {
            throw new Exception("Add date to link");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(text_date);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return orderService.getAllOrdersByDate(sqlStartDate);
    }
    @RequestMapping("/orders/by-product")
    public List<OrderDTO> showAllOrdersByProduct(@RequestBody Product product) {

        return orderService.getAllOrdersByProduct(product);
    }
    //TODO change product and customer class to id, not full class
    @RequestMapping("/orders/by-customer")
    public List<OrderDTO> showAllOrdersByCustomer(@RequestBody Customer customer) throws Exception {
        if (customer.getRegistrationCode() == 0) {
            throw new Exception("Add valid registartion code");
        }
        return orderService.getAllOrdersByCustomer(customer);
    }


}
