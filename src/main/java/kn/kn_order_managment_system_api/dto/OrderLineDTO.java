package kn.kn_order_managment_system_api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderLineDTO {
    private int orderLineId;
    private int orderId;
    private int productId;
    private int quantity;

    public OrderLineDTO() {
    }

    public OrderLineDTO(int orderLineId, int orderId, int productId, int quantity) {
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
