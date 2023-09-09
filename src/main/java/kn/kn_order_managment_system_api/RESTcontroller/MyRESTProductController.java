package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.CustomerDTO;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.entity.Customer;
import kn.kn_order_managment_system_api.entity.Product;
import kn.kn_order_managment_system_api.services.CustomerService;
import kn.kn_order_managment_system_api.services.OrderLineService;
import kn.kn_order_managment_system_api.services.OrderService;
import kn.kn_order_managment_system_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/products")
    public List<ProductDTO> showAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO product) {
        productService.saveProduct(product);
        return product;
    }

}
