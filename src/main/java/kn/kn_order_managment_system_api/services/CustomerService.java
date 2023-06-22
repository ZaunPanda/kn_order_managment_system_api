package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomer(int customer_id);
    void deleteCustomer(int customer_id);
}
