package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.entity.OrderLine;

import java.util.List;

public interface OrderLineService {
    List<OrderLineDTO> getAllOrderLines();
    void saveOrderLine(OrderLineDTO orderLine);
    OrderLineDTO getOrderLine(int orderLine_id);
    void deleteOrderLine(int orderLine_id);
}
