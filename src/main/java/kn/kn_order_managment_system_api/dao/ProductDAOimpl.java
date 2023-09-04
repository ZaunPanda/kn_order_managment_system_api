package kn.kn_order_managment_system_api.dao;

import jakarta.persistence.*;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDAOimpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private ModelMapper modelMapper;
    @Override
    public List<ProductDTO> getAllProducts() {
        Query query = entityManager.createQuery("from Product ");
        List<ProductDTO> allProducts= query.getResultList();
        return allProducts;
    }
    @Override
    public void saveProduct(ProductDTO productDTO) {
        Product NewProduct = modelMapper.map(productDTO, Product.class);
        entityManager.merge(NewProduct);
    }

    @Override
    public ProductDTO getProduct(int product_id) {
        ProductDTO productDTO =  entityManager.find(ProductDTO.class,product_id);
        return productDTO;
    }

    @Override
    public void deleteProduct(int product_id) {
        Query query = entityManager.createQuery("delete from Product where productId=:product_id");
        query.executeUpdate();
    }
}
