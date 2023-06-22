package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.entity.OrderLine;

import java.util.List;

public interface OrderLineDAO {
    List<OrderLine> getAllOrderLines();
    void saveOrderLine(OrderLine orderLine);
    OrderLine getOrderLine(int orderLine_id);
    void deleteOrderLine(int orderLine_id);

}
