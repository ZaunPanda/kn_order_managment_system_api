package kn.kn_order_managment_system_api.controllers;

import kn.kn_order_managment_system_api.dto.OrderLineDTO;

import kn.kn_order_managment_system_api.services.interfaces.OrderLineService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    @GetMapping("/order_lines")
    public List<OrderLineDTO> showAllOrderLines() {
        return orderLineService.getAllOrderLines();
    }


}
