package kn.kn_order_managment_system_api.Repository;

import jakarta.persistence.*;
import jakarta.persistence.Query;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderLineDAO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.entity.OrderLine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderLineDAOImpl implements OrderLineDAO {
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<OrderLineDTO> getAllOrderLines() {
        Query query = entityManager.createQuery("from OrderLine ");
        List<OrderLineDTO> allOrderLines= query.getResultList();
        return allOrderLines;
    }

    @Override
    public void saveOrderLine(OrderLineDTO orderLineDTO) {
        OrderLine newOrderLine = modelMapper.map(orderLineDTO, OrderLine.class);
        entityManager.persist(newOrderLine);
    }

    @Override
    public OrderLineDTO getOrderLine(int orderLineId) {
        OrderLine orderLine =  entityManager.find(OrderLine.class,orderLineId);

        if (orderLine != null) {
            return modelMapper.map(orderLine, OrderLineDTO.class);
        } else {
            return null;
        }

    }

    @Override
    public void deleteOrderLine(int orderLineId) {
        Query query = entityManager.createQuery("delete from OrderLine where orderLineId=:orderLineId");
        query.setParameter("orderLineId", orderLineId);
        query.executeUpdate();
        entityManager.clear();

    }
}
