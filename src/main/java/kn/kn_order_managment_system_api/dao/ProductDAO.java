package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {
    List<ProductDTO> getAllProducts();

    void saveProduct(ProductDTO productDTO);

    ProductDTO getProduct(int product_id);

    void deleteProduct(int product_id);
}
