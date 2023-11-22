package kn.kn_order_managment_system_api.Repository.interfaces;

import kn.kn_order_managment_system_api.dto.OrderDTO;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.util.List;

public interface OrderDAO {
    List<OrderDTO> getAllOrders();

    void saveOrder(OrderDTO order);

    OrderDTO getOrder(int order_id);

    List<OrderDTO> findAll(Specification cs);

    void deleteOrder(int order_id);


}
