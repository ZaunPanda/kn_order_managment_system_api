package kn.kn_order_managment_system_api.Repository;

import jakarta.persistence.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;

import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO {
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
    public List<OrderDTO> findAll(Specification specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDTO> query = builder.createQuery(OrderDTO.class);
        Root<Order> root = query.from(Order.class);

        // Применяем спецификацию к CriteriaQuery
        query.where(specification.toPredicate(root, query, builder));

        // Проектируем результат в CustomerDTO
        query.select(builder.construct(
                OrderDTO.class,
                root.get("orderId"),
                root.get("customerId"),
                root.get("submissionDate")
        ));

        List<OrderDTO> result = entityManager.createQuery(query)
                .getResultList();

        return result;
    }

    @Override
    public void deleteOrder(int order_id) {
        Query query = entityManager.createQuery("delete from Order where orderId=:order_id");
        query.setParameter("order_id", order_id);
        query.executeUpdate();
        entityManager.clear();
    }
}
