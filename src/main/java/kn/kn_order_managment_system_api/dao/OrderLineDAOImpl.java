package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.*;
import jakarta.persistence.Query;
import kn.kn_order_managment_system_api.entity.OrderLine;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderLineDAOImpl implements OrderLineDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<OrderLine> getAllOrderLines() {
        Query query = entityManager.createQuery("from OrderLine ");
        List<OrderLine> allOrderLines= query.getResultList();
        return allOrderLines;
    }

    @Override
    public void saveOrderLine(OrderLine orderLine) {
        OrderLine NewOrderLine = entityManager.merge(orderLine);
        orderLine.setOrderLineId(NewOrderLine.getOrderLineId());
    }

    @Override
    public OrderLine getOrderLine(int orderLineId) {
        OrderLine orderLine =  entityManager.find(OrderLine.class,orderLineId);
        return orderLine;
    }

    @Override
    public void deleteOrderLine(int orderLineId) {
        Query query = entityManager.createQuery("delete from OrderLine where orderLineId=:orderLineId");
        query.executeUpdate();

    }
}
