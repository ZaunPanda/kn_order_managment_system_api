package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.entity.OrderLine;

import java.util.List;

public interface OrderLineDAO {
    List<OrderLineDTO> getAllOrderLines();

    void saveOrderLine(OrderLineDTO orderLineDTO);

    OrderLineDTO getOrderLine(int orderLine_id);

    void deleteOrderLine(int orderLine_id);

}
