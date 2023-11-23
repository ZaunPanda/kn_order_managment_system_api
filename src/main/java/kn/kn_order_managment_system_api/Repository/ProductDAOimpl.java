package kn.kn_order_managment_system_api.Repository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import kn.kn_order_managment_system_api.Repository.interfaces.ProductDAO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDAOimpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
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
        entityManager.persist(NewProduct);
    }

    @Override
    public ProductDTO getProduct(int product_id) {
        Product DBproduct =  entityManager.find(Product.class,product_id);
        if (DBproduct != null) {
            return modelMapper.map(DBproduct, ProductDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<ProductDTO> findAll(Specification specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductDTO> query = builder.createQuery(ProductDTO.class);
        Root<Product> root = query.from(Product.class);

        // Применяем спецификацию к CriteriaQuery
        query.where(specification.toPredicate(root, query, builder));

        // Проектируем результат в CustomerDTO
        query.select(builder.construct(
                ProductDTO.class,
                root.get("productId"),
                root.get("productName"),
                root.get("skuCode"),
                root.get("unitPrice")
        ));

        List<ProductDTO> result = entityManager.createQuery(query)
                .getResultList();

        return result;
    }

    @Override
    public void deleteProduct(int product_id) {
        Query query = entityManager.createQuery("delete from Product where productId=:product_id");
        query.setParameter("product_id", product_id);
        query.executeUpdate();
        entityManager.clear();
    }
}
