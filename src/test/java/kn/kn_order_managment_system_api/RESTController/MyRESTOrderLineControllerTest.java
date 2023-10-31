package kn.kn_order_managment_system_api.RESTController;

import kn.kn_order_managment_system_api.OrderController.MyRESTOrderLineController;
import kn.kn_order_managment_system_api.dto.OrderDTO;
import kn.kn_order_managment_system_api.dto.OrderLineDTO;
import kn.kn_order_managment_system_api.dto.ProductDTO;
import kn.kn_order_managment_system_api.services.interfaces.OrderLineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyRESTOrderLineController.class)
public class MyRESTOrderLineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderLineService orderLineService;

    @Test
    public void MyRESTController_showAllOrderLines_ReturnAllOrderLines() throws Exception{
        List<OrderLineDTO> allOrderLines = new ArrayList<>();

        OrderDTO order1 = OrderDTO.builder()
                .customerId(1)
                .submissionDate(LocalDate.now())
                .build();

        order1.setOrderId(1);
        ProductDTO product1 = ProductDTO.builder().productName("Tea").skuCode("EU883311").unitPrice("12").build();
        product1.setProductId(1);
        ProductDTO product2 = ProductDTO.builder().productName("Coffee").skuCode("EU812311").unitPrice("6").build();
        product2.setProductId(2);

        OrderLineDTO orderLineDTO = OrderLineDTO.builder().orderId(order1.getOrderId()).productId(product1.getProductId()).quantity(10).build();
        OrderLineDTO orderLineDTO2 = OrderLineDTO.builder().orderId(order1.getOrderId()).productId(product2.getProductId()).quantity(50).build();

        allOrderLines.add(orderLineDTO);
        allOrderLines.add(orderLineDTO2);

        given(orderLineService.getAllOrderLines()).willReturn(allOrderLines);

        ResultActions response = mockMvc.perform(get("/api/order_lines"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(allOrderLines.size())));


    }
}
