package kn.kn_order_managment_system_api.services;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.dao.OrderLineDAO;
import kn.kn_order_managment_system_api.entity.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderLineServiceImpl implements OrderLineService{

    @Autowired
    private OrderLineDAO orderLineDAO;

    @Override
    @Transactional
    public List<OrderLine> getAllOrderLines() {
        return orderLineDAO.getAllOrderLines();
    }

    @Override
    @Transactional
    public void saveOrderLine(OrderLine orderLine) {
        orderLineDAO.saveOrderLine(orderLine);
    }

    @Override
    @Transactional
    public OrderLine getOrderLine(int orderLine_id) {
        return orderLineDAO.getOrderLine(orderLine_id);
    }

    @Override
    @Transactional
    public void deleteOrderLine(int orderLine_id) {
        orderLineDAO.deleteOrderLine(orderLine_id);

    }
}
