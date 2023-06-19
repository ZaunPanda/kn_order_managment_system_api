package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kn.kn_order_managment_system_api.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerDAOImpl implements CustomerDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Customer> getAllCustomers() {

        Query query = entityManager.createQuery("from Customer ");
        List<Customer> allCustomers = query.getResultList();
        return allCustomers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Customer newCustomer = entityManager.merge(customer);
        customer.setRegistrationCode(newCustomer.getRegistrationCode());

    }

    @Override
    public Customer getCustomer(int RegistrationCode) {
        Customer customer =  entityManager.find(Customer.class,RegistrationCode);
        return customer;
    }

    @Override
    public void deleteCustomer(int RegistrationCode) {
        Query query = entityManager.createQuery("delete from Customer where registrationCode=:RegistrationCode");
//        query.setParameter("registrationCode",RegistrationCode);
        query.executeUpdate();

    }
}
