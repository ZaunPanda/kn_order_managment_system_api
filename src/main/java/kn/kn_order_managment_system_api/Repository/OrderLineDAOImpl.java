package kn.kn_order_managment_system_api.Repository;

import jakarta.persistence.*;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import kn.kn_order_managment_system_api.Repository.interfaces.OrderLineDAO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.OrderLine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public List<OrderLineDTO> findAll(Specification specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderLineDTO> query = builder.createQuery(OrderLineDTO.class);
        Root<OrderLine> root = query.from(OrderLine.class);

        query.where(specification.toPredicate(root, query, builder));

        query.select(builder.construct(
                OrderLineDTO.class,
                root.get("orderLineId"),
                root.get("orderId"),
                root.get("productId"),
                root.get("quantity")
        ));

        List<OrderLineDTO> result = entityManager.createQuery(query)
                .getResultList();

        return result;
    }

    @Override
    public void deleteOrderLine(int orderLineId) {
        Query query = entityManager.createQuery("delete from OrderLine where orderLineId=:orderLineId");
        query.setParameter("orderLineId", orderLineId);
        query.executeUpdate();
        entityManager.clear();

    }
}
