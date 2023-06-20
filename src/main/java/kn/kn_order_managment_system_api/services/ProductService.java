package kn.kn_order_managment_system_api.services;

import kn.kn_order_managment_system_api.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public void saveProduct(Product product);
    public Product getProduct(int product_id);
    public void deleteProduct(int product_id);
}
