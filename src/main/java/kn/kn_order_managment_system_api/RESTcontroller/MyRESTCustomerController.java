package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.*;
import kn.kn_order_managment_system_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTCustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/customers")
    public List<CustomerDTO> showAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable int id) throws Exception {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customer) throws Exception {
        if (customer.getFullName() == null) {
            throw new Exception("Add name of customer");
        }
        customerService.saveCustomer(customer);
        return customer;
    }
    @DeleteMapping("/customers_delete/{id}")
    public void deleteCustomer(@PathVariable int id) throws Exception {
        customerService.deleteCustomer(id);
    }
}
