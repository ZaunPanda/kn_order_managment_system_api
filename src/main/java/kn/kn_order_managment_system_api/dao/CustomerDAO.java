package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface CustomerDAO {

    List<CustomerDTO> getAllCustomers();
    void saveCustomer(CustomerDTO customer);
    CustomerDTO getCustomer(int customer_id);
    void deleteCustomer(int customer_id);
}
