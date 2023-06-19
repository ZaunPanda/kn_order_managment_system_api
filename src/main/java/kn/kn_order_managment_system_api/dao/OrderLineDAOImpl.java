package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.*;
import jakarta.persistence.Query;
import kn.kn_order_managment_system_api.entity.Order;
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
        orderLine.setOrderLine_id(NewOrderLine.getOrderLine_id());
    }

    @Override
    public OrderLine getOrderLine(int orderLine_id) {
        OrderLine orderLine =  entityManager.find(OrderLine.class,orderLine_id);
        return orderLine;
    }

    @Override
    public void deleteOrderLine(int orderLine_id) {
        Query query = entityManager.createQuery("delete from OrderLine where orderLine_id=:orderLine_id");
        query.executeUpdate();

    }
}
