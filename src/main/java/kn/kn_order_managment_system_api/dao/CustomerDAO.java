package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.entity.Customer;

import java.util.List;
public interface CustomerDAO {

    List<CustomerDTO> getAllCustomers();
    Customer saveCustomer(CustomerDTO customer);
    CustomerDTO getCustomer(int customer_id) throws Exception;
    void deleteCustomer(int customer_id) throws Exception;
}
