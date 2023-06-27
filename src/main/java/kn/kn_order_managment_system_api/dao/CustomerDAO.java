package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import org.apache.catalina.mapper.Mapper;

import java.util.List;

public interface CustomerDAO {

    List<CustomerDTO> getAllCustomers();
    void saveCustomer(Customer customer);
    CustomerDTO getCustomer(int customer_id);
    void deleteCustomer(int customer_id);
}
