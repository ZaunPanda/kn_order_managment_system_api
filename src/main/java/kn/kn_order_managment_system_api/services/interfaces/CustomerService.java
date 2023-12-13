package kn.kn_order_managment_system_api.services.interfaces;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO saveCustomer(CustomerDTO customer);

    public CustomerDTO getCustomer(int customer_id) throws Exception;

    void  deleteCustomer(int customer_id) throws Exception;

    public List<CustomerDTO> findAll(Specification cs) throws Exception;
}
