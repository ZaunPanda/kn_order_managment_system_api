package kn.kn_order_managment_system_api.OrderController;

import kn.kn_order_managment_system_api.dto.OrderDTO;

import kn.kn_order_managment_system_api.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDTO> showAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderDTO getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO saveOrder(@RequestBody OrderDTO order) {
        order.setSubmissionDate(LocalDate.now());
        orderService.saveOrder(order);
        return order;
    }


    @GetMapping("/orders/by-date/{text_date}")
    public List<OrderDTO> getAllOrdersByDate(@PathVariable String text_date) throws Exception {
        if (text_date == null) {
            throw new IllegalArgumentException("Date is missing");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(text_date);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        return orderService.getAllOrdersByDate(sqlStartDate);
    }

    @GetMapping("/orders/by-product/{productId}")
    public List<OrderDTO> showAllOrdersByProduct(@PathVariable int productId) {

        return orderService.getAllOrdersByProduct(productId);
    }

    @GetMapping("/orders/by-customer/{customerId}")
    public List<OrderDTO> showAllOrdersByCustomer(@PathVariable int customerId) throws Exception {
        if (customerId == 0) {
            throw new Exception("Add valid registartion code");
        }
        return orderService.getAllOrdersByCustomer(customerId);
    }

    @DeleteMapping("/orders/delete/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }


}
