package kn.kn_order_managment_system_api.Repository.interfaces;

import kn.kn_order_managment_system_api.dto.OrderLineDTO;

import java.util.List;

public interface OrderLineDAO {
    List<OrderLineDTO> getAllOrderLines();

    void saveOrderLine(OrderLineDTO orderLineDTO);

    OrderLineDTO getOrderLine(int orderLine_id);

    void deleteOrderLine(int orderLine_id);

}
