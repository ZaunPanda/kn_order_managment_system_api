package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerDAOImpl implements CustomerDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CustomerDTO>  getAllCustomers() {
        Query query = entityManager.createQuery("from Customer ");
        List<CustomerDTO>  allCustomers = query.getResultList();
        return allCustomers;
    }

    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer newCustomer = modelMapper.map(customerDTO, Customer.class);
        entityManager.persist(newCustomer);

        return newCustomer;
    }

    @Override
    public CustomerDTO getCustomer(int registrationCode) {
        Query query = entityManager.createQuery("from Customer с where с.registrationCode=:RegistrationCode");
        query.setParameter("RegistrationCode", registrationCode);
        List<CustomerDTO>  allCustomers = query.getResultList();

        return allCustomers.get(0);
    }


    @Override
    public void deleteCustomer(int RegistrationCode) {
        Customer customer = entityManager.find(Customer.class, RegistrationCode);
        if (customer != null) {
            entityManager.remove(customer);
        } else {
            // Handle the case where the customer with the specified registration code doesn't exist.
            // You can throw an exception or log an error message here.
        }

    }
}
