package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.dto.OrderDTO;


import java.sql.Date;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    void saveOrder(OrderDTO order);
    OrderDTO getOrder(int order_id);
    List<OrderDTO> getAllOrdersByDate(Date date);
    List<OrderDTO> getAllOrdersByProduct(int productId);
    List<OrderDTO> getAllOrdersByCustomer(int customerId);

    void deleteOrder(int order_id);
}
