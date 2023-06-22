package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.*;
import kn.kn_order_managment_system_api.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDAOimpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Product> getAllProducts() {
        Query query = entityManager.createQuery("from Product ");
        List<Product> allProducts= query.getResultList();
        return allProducts;
    }
    @Override
    public void saveProduct(Product product) {
        Product NewProduct = entityManager.merge(product);
        product.setProductId(NewProduct.getProductId());
    }

    @Override
    public Product getProduct(int product_id) {
        Product product =  entityManager.find(Product.class,product_id);
        return product;
    }

    @Override
    public void deleteProduct(int product_id) {
        Query query = entityManager.createQuery("delete from Product where product_id=:product_id");
        query.executeUpdate();
    }
}
