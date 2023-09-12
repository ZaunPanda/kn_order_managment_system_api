package kn.kn_order_managment_system_api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDTO {

    private int orderId;
    private int customerId;
    private String submissionDate;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, int customerId, String submissionDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.submissionDate = submissionDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

}
