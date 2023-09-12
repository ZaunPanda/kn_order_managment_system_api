package kn.kn_order_managment_system_api.RESTController;

import com.fasterxml.jackson.databind.ObjectMapper;
import kn.kn_order_managment_system_api.RESTcontroller.MyRESTProductController;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(MyRESTProductController.class)
public class MyRESTProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void MyRESTController_getAllProducts_ReturnAllProducts() throws Exception{
        List<ProductDTO> allProducts = new ArrayList<>();

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice("12").build();
        product1.setProductId(1);
        ProductDTO product2 = ProductDTO.builder().productName("Coffee").skuCode("EU812311").unitPrice("6").build();
        product2.setProductId(2);



        allProducts.add(product1);
        allProducts.add(product2);

        given(productService.getAllProducts()).willReturn(allProducts);

        ResultActions response = mockMvc.perform(get("/api/products"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(allProducts.size())));


    }

    @Test
    public void MyRESTController_saveProduct_ReturnOk() throws Exception{

        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice("12").build();
        product1.setProductId(1);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product1)));

        verify(productService).saveProduct(product1);





    }

}
