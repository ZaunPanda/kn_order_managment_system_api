package kn.kn_order_managment_system_api.services;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.dao.OrderDAO;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class OrderServicesImpl implements OrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
    orderDAO.saveOrder(order);
    }

    @Override
    @Transactional
    public Order getOrder(int order_id) {
        return orderDAO.getOrder(order_id);
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByDate(Date date) {
        return orderDAO.getAllOrdersByDate(date);
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByProduct(Product product) {

        return orderDAO.getAllOrdersByProduct(product);
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByCustomer(Customer customer) {
        return orderDAO.getAllOrdersByCustomer(customer);
    }

    @Override
    @Transactional
    public void deleteOrder(int order_id) {
        orderDAO.deleteOrder(order_id);
    }
}
