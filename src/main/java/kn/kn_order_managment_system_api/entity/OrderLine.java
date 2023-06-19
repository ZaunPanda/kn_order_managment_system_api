package kn.kn_order_managment_system_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderLines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLine_id")
    private int orderLine_id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    @JoinColumn(name = "quantity")
    private int quantity;

    public OrderLine() {
    }

    public OrderLine(Order order_id, Product product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getOrderLine_id() {
        return orderLine_id;
    }

    public void setOrderLine_id(int orderLine_id) {
        this.orderLine_id = orderLine_id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
