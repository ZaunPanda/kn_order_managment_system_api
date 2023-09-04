package kn.kn_order_managment_system_api.services;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.dao.OrderDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.entity.Customer;
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
    public List<OrderDTO> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    @Transactional
    public void saveOrder(OrderDTO order) {
    orderDAO.saveOrder(order);
    }

    @Override
    @Transactional
    public OrderDTO getOrder(int order_id) {
        return orderDAO.getOrder(order_id);
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrdersByDate(Date date) {
        return orderDAO.getAllOrdersByDate(date);
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrdersByProduct(Product product) {

        return orderDAO.getAllOrdersByProduct(product);
    }

    @Override
    public List<OrderDTO> getAllOrdersByCustomer(Customer customerId) {
        return null;
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrdersByCustomer(CustomerDTO customerId) {
        return orderDAO.getAllOrdersByCustomer(customerId);
    }

    @Override
    @Transactional
    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }
}
