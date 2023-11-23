package kn.kn_order_managment_system_api.OrderController;

import kn.kn_order_managment_system_api.OrderController.models.OrderSearchCriteria;
import kn.kn_order_managment_system_api.OrderController.models.OrderSpecifications;
import kn.kn_order_managment_system_api.OrderController.models.ProductSearchCriteria;
import kn.kn_order_managment_system_api.OrderController.models.ProductSpecifications;
import kn.kn_order_managment_system_api.Repository.interfaces.ProductDAO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import kn.kn_order_managment_system_api.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> showAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(@RequestBody ProductDTO product) {
        productService.saveProduct(product);
        return product;
    }
    @GetMapping("/products/search")
    public List<ProductDTO> searchCustomers(@RequestBody ProductSearchCriteria criteria) throws Exception {
        Specification<Product> specification = ProductSpecifications.buildSpecification(criteria);
        return productService.findAll(specification);
    }

}
