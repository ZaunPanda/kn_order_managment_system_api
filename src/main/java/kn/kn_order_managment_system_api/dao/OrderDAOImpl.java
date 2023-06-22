package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.*;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Order> getAllOrders() {
        Query query = entityManager.createQuery("from Order ");
        List<Order> allOrders= query.getResultList();
        return allOrders;
    }

    @Override
    public void saveOrder(Order order) {
        Order newOrder = entityManager.merge(order);
        order.setOrderId(newOrder.getOrderId());

    }

    @Override
    public Order getOrder(int order_id) {
        Order order =  entityManager.find(Order.class,order_id);
        return order;
    }

    @Override
    public List<Order> getAllOrdersByDate(Date date) {
        Query query = entityManager.createQuery("from Order where submissionDate=:date");
        query.setParameter("date", date.toString());
        List<Order> allOrdersByDate= query.getResultList();
        return allOrdersByDate;
    }

    @Override
    public List<Order> getAllOrdersByProduct(Product product) {
        Query query = entityManager.createQuery("SELECT orderId FROM OrderLine WHERE productId = :product");
        query.setParameter("product", product);
        List<Order> allOrdersByProduct = query.getResultList();

        return allOrdersByProduct;
    }

    @Override
    public List<Order> getAllOrdersByCustomer(Customer customerId) {
        Query query = entityManager.createQuery("FROM Order WHERE customerId = :customerId");
        query.setParameter("customerId", customerId);
        List<Order> allOrdersBycustomer= query.getResultList();
        return allOrdersBycustomer;
    }

    @Override
    public void deleteOrder(int order_id) {
        Query query = entityManager.createQuery("delete from Order where orderId=:order_id");
        query.executeUpdate();
    }
}
