package kn.kn_order_managment_system_api.OrderController.models;

public class OrderLineSearchCriteria {
    private String orderLineId;
    private String orderId;
    private String productId;
    private String quantity;

    public OrderLineSearchCriteria() {
    }

    public OrderLineSearchCriteria(String orderLineId, String orderId, String productId, String quantity) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
