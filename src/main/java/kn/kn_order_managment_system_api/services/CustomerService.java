package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();
    public void saveCustomer(Customer customer);
    public CustomerDTO getCustomer(int customer_id);
    public void deleteCustomer(int customer_id);
}
