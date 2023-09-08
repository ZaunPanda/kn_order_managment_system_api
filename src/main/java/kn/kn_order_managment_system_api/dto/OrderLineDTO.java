package kn.kn_order_managment_system_api.dto;

import kn.kn_order_managment_system_api.entity.Order;
import kn.kn_order_managment_system_api.entity.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderLineDTO {
    private int orderLineId;
    private Order orderId;
    private Product productId;
    private int quantity;

    public OrderLineDTO() {
    }

    public OrderLineDTO(int orderLineId, Order orderId, Product productId, int quantity) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
