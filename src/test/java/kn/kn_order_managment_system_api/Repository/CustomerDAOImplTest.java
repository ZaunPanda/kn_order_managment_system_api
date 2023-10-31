package kn.kn_order_managment_system_api.Repository;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.Repository.interfaces.CustomerDAO;
import kn.kn_order_managment_system_api.dto.CustomerDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerDAOImplTest {
    @Autowired
    private CustomerDAO customerDAO;

    @Test
    public void CustomerDAO_getAllCustomers_ReturnCustomers() {

        CustomerDTO customer1 = CustomerDTO.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .fullName("CustomerTwo")
                .email("email2@email.com")
                .telephone("+372000000")
                .build();

        customerDAO.saveCustomer(customer1);
        customerDAO.saveCustomer(customer2);

        List<CustomerDTO> allCustomers = customerDAO.getAllCustomers();
        Assertions.assertThat(allCustomers).isNotNull();
        Assertions.assertThat(allCustomers.size()).isEqualTo(2);
    }
    @Test
    public void CustomerDAO_saveCustomer_ReturnCustomer() throws Exception {

        CustomerDTO customer = CustomerDTO.builder()
                .fullName("CustomerSaveCustomer")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        customerDAO.saveCustomer(customer);

        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Assertions.assertThat(retrievedCustomer.getFullName()).isEqualTo(customer.getFullName());
    }
    @Test
    public void CustomerDAO_getCustomer_ReturnCustomer() throws Exception {

        CustomerDTO customer = CustomerDTO.builder()
                .fullName("CustomerGetCustomer")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        customerDAO.saveCustomer(customer);

        CustomerDTO retrievedCustomer = customerDAO.getCustomer(1);
        Assertions.assertThat(retrievedCustomer.getFullName()).isEqualTo(customer.getFullName());
    }

    @Test
    public void CustomerDAO_deleteCustomer_ReturnCustomer() throws Exception {

        CustomerDTO customer = CustomerDTO.builder()
                .fullName("CustomerOne")
                .email("email@email.com")
                .telephone("+37255667744")
                .build();

        customerDAO.saveCustomer(customer);
        customerDAO.deleteCustomer(1);

        CustomerDTO customerDTO = customerDAO.getCustomer(1);
        Assertions.assertThat(customerDTO).isNull();

    }



}
