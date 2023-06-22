package kn.kn_order_managment_system_api.dao;

import kn.kn_order_managment_system_api.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProduct(int product_id);
    void deleteProduct(int product_id);
}
