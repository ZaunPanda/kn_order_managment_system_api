package kn.kn_order_managment_system_api.RESTcontroller;

import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(@RequestBody ProductDTO product) {
        productService.saveProduct(product);
        return product;
    }

}
