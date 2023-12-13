package kn.kn_order_managment_system_api.services;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.Repository.interfaces.CustomerDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    @Transactional
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerDAO.saveCustomer(customerDTO);
        return customerDTO;
    }

    @Override
    @Transactional
    public CustomerDTO getCustomer(int customer_id) throws Exception {
        return customerDAO.getCustomer(customer_id);
    }

    @Transactional
    @Override
    public void deleteCustomer(int customer_id) throws Exception {
        customerDAO.deleteCustomer(customer_id);
    }

    @Override
    @Transactional
    public List<CustomerDTO> findAll(Specification cs) throws Exception {
        return customerDAO.findAll(cs);
    }

}
