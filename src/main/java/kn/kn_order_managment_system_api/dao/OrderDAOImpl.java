package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.*;

import kn.kn_order_managment_system_api.dto.OrderDTO;

import kn.kn_order_managment_system_api.entity.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<OrderDTO> getAllOrders() {
        Query query = entityManager.createQuery("from Order ");
        List<OrderDTO> allOrders= query.getResultList();
        return allOrders;
    }
    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order newOrder = modelMapper.map(orderDTO, Order.class);
        entityManager.persist(newOrder);
    }

    @Override
    public OrderDTO getOrder(int order_id) {
        Order order = entityManager.find(Order.class, order_id);
        if (order != null) {
            OrderDTO newOrder = modelMapper.map(order, OrderDTO.class);
            return newOrder;
        } else {
            return null;

        }
    }

    @Override
    public List<OrderDTO> getAllOrdersByDate(Date date) {
        Query query = entityManager.createQuery("from Order where submissionDate=:date");
        query.setParameter("date", date.toString());
        List<OrderDTO> allOrdersByDate= query.getResultList();
        return allOrdersByDate;
    }

    @Override
    public List<OrderDTO> getAllOrdersByProduct(int productId) {
        Query query = entityManager.createQuery("SELECT orderId FROM OrderLine WHERE productId = :product");
        query.setParameter("product", productId);
        List<OrderDTO> allOrdersByProduct = query.getResultList();

        return allOrdersByProduct;
    }

    @Override
    public List<OrderDTO> getAllOrdersByCustomer(int customerId) {
        Query query = entityManager.createQuery("FROM Order WHERE customerId = :customer");
        query.setParameter("customer", customerId);
        List<OrderDTO> allOrdersByCustomer = query.getResultList();
        return allOrdersByCustomer;
    }

    @Override
    public void deleteOrder(int order_id) {
        Query query = entityManager.createQuery("delete from Order where orderId=:order_id");
        query.setParameter("order_id", order_id);
        query.executeUpdate();
        entityManager.clear();
    }
}
