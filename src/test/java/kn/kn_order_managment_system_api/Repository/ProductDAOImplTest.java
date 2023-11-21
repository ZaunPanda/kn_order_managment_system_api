package kn.kn_order_managment_system_api.Repository;

import jakarta.transaction.Transactional;

import kn.kn_order_managment_system_api.Repository.interfaces.ProductDAO;
import kn.kn_order_managment_system_api.dto.ProductDTO;

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
public class ProductDAOImplTest {
    @Autowired
    private ProductDAO productDAO;

    @Test
    public void ProductDAO_getAllProducts_ReturnAllProducts(){


        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice(12).build();
        ProductDTO product2 = ProductDTO.builder().productName("Coffee").skuCode("EU812311").unitPrice(6).build();
        productDAO.saveProduct(product1);
        productDAO.saveProduct(product2);
        List<ProductDTO> allProducts = productDAO.getAllProducts();

        Assertions.assertThat(allProducts.size()).isEqualTo(2);
    }
    @Test
    public void ProductDAO_saveProduct_ReturnSavedProduct() {

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice(12).build();
        productDAO.saveProduct(product1);
        ProductDTO retrievedProduct = productDAO.getProduct(1);

        Assertions.assertThat(product1.getSkuCode()).isEqualTo(retrievedProduct.getSkuCode());
    }
    @Test
    public void ProductDAO_getProduct_ReturnProduct() {

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice(12).build();
        productDAO.saveProduct(product1);
        ProductDTO retrievedProduct = productDAO.getProduct(1);

        Assertions.assertThat(retrievedProduct).isNotNull();
    }
    @Test
    public void ProductDAO_deleteProduct_ReturnNull() {

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice(12).build();
        productDAO.saveProduct(product1);
        productDAO.deleteProduct(1);
        ProductDTO retrievedProduct = productDAO.getProduct(1);

        Assertions.assertThat(retrievedProduct).isNull();
    }

}
