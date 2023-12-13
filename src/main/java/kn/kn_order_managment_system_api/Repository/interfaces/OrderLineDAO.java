package kn.kn_order_managment_system_api.Repository.interfaces;

import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OrderLineDAO {
    List<OrderLineDTO> getAllOrderLines();

    void saveOrderLine(OrderLineDTO orderLineDTO);

    OrderLineDTO getOrderLine(int orderLine_id);

    List<OrderLineDTO> findAll(Specification cs);

    void deleteOrderLine(int orderLine_id);

}
