package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();
    public void saveCustomer(CustomerDTO customer);
    public CustomerDTO getCustomer(int customer_id) throws Exception;
    void  deleteCustomer(int customer_id) throws Exception;
}
