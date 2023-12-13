package kn.kn_order_managment_system_api.services.interfaces;

import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.entity.OrderLine;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OrderLineService {
    List<OrderLineDTO> getAllOrderLines();

    void saveOrderLine(OrderLineDTO orderLine);

    OrderLineDTO getOrderLine(int orderLine_id);

    List<OrderLineDTO> findAll(Specification cs) throws Exception;

    void deleteOrderLine(int orderLine_id);
}
