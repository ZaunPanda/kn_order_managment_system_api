package kn.kn_order_managment_system_api.services;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.dao.OrderLineDAO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderLineServiceImpl implements OrderLineService{

    @Autowired
    private OrderLineDAO orderLineDAO;

    @Override
    @Transactional
    public List<OrderLineDTO> getAllOrderLines() {
        return orderLineDAO.getAllOrderLines();
    }

    @Override
    @Transactional
    public void saveOrderLine(OrderLineDTO orderLineDTO) {
        orderLineDAO.saveOrderLine(orderLineDTO);
    }

    @Override
    @Transactional
    public OrderLineDTO getOrderLine(int orderLine_id) {
        return orderLineDAO.getOrderLine(orderLine_id);
    }

    @Override
    @Transactional
    public void deleteOrderLine(int orderLine_id) {
        orderLineDAO.deleteOrderLine(orderLine_id);

    }
}
