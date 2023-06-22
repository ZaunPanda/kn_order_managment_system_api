package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;

import java.sql.Date;
import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders();
    void saveOrder(Order order);
    Order getOrder(int order_id);
    List<Order> getAllOrdersByDate(Date date);
    List<Order> getAllOrdersByProduct(Product product);
    List<Order> getAllOrdersByCustomer(Customer customerId);
    void deleteOrder(int order_id);
}
