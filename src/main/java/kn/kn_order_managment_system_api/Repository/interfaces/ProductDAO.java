package kn.kn_order_managment_system_api.Repository.interfaces;

import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductDAO {
    List<ProductDTO> getAllProducts();

    void saveProduct(ProductDTO productDTO);

    ProductDTO getProduct(int product_id);
    List<ProductDTO> findAll(Specification cs);

    void deleteProduct(int product_id);
}
