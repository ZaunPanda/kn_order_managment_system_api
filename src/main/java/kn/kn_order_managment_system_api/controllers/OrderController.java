package kn.kn_order_managment_system_api.controllers;

import kn.kn_order_managment_system_api.controllers.models.OrderSearchCriteria;
import kn.kn_order_managment_system_api.controllers.models.OrderSpecifications;
import kn.kn_order_managment_system_api.dto.OrderDTO;

import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
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
    @GetMapping("/orders/search")
    public List<OrderDTO> searchCustomers(@RequestBody OrderSearchCriteria criteria) throws Exception {
        Specification<Order> specification = OrderSpecifications.buildSpecification(criteria);
        return orderService.findAll(specification);
    }

    @DeleteMapping("/orders/delete/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }


}
