package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.entity.Product;

import java.sql.Date;
import java.util.List;

public interface OrderDAO {
    List<OrderDTO> getAllOrders();
    void saveOrder(OrderDTO order);
    OrderDTO getOrder(int order_id);
    List<OrderDTO> getAllOrdersByDate(Date date);
    List<OrderDTO> getAllOrdersByProduct(Product product);
    List<OrderDTO> getAllOrdersByCustomer(CustomerDTO customerId);

    List<OrderDTO> getAllOrdersByCustomer(Customer customerId);

    void deleteOrder(int order_id);
}
