package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;

import java.sql.Date;
import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public void saveOrder(Order order);
    public Order getOrder(int order_id);
    public List<Order> getAllOrdersByDate(Date date);
    public List<Order> getAllOrdersByProduct(Product product);
    public List<Order> getAllOrdersByCustomer(Customer customer);
    public void deleteOrder(int order_id);
}
