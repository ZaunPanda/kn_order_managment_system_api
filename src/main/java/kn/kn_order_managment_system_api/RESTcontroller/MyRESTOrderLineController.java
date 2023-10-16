package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.OrderLineDTO;

import kn.kn_order_managment_system_api.services.OrderLineService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTOrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    @RequestMapping("/order_lines")
    public List<OrderLineDTO> showAllOrderLines() {
        return orderLineService.getAllOrderLines();
    }


}
