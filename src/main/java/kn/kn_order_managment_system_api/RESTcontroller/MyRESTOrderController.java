package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.OrderDTO;

import kn.kn_order_managment_system_api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
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
    @RequestMapping("/orders/by-product/{productId}")
    public List<OrderDTO> showAllOrdersByProduct(@PathVariable int productId) {

        return orderService.getAllOrdersByProduct(productId);
    }
    @RequestMapping("/orders/by-customer/{customerId}")
    public List<OrderDTO> showAllOrdersByCustomer(@PathVariable int customerId) throws Exception {
        if (customerId == 0) {
            throw new Exception("Add valid registartion code");
        }
        return orderService.getAllOrdersByCustomer(customerId);
    }

    @RequestMapping("/orders/delete/{oderId}")
    public void deleteOrder(@PathVariable int oderId) throws Exception {
        if (oderId == 0) {
            throw new Exception("Add valid order ID");
        }
        else {
            orderService.deleteOrder(oderId);
        }
    }


}
