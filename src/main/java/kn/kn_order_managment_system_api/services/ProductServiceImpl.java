package kn.kn_order_managment_system_api.services;

import jakarta.transaction.Transactional;
import kn.kn_order_managment_system_api.Repository.interfaces.ProductDAO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        productDAO.saveProduct(productDTO);
    }

    @Override
    @Transactional
    public ProductDTO getProduct(int product_id) {
        return productDAO.getProduct(product_id);
    }

    @Override
    @Transactional
    public void deleteProduct(int product_id) {
        productDAO.deleteProduct(product_id);
    }
}
