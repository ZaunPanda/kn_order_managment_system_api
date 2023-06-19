package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getAllCustomers();
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int customer_id);
    public void deleteCustomer(int customer_id);
}
