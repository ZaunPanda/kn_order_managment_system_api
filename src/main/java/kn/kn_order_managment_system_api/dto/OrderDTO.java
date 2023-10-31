package kn.kn_order_managment_system_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Builder
@Data
public class OrderDTO {

    private int orderId;
    private int customerId;
    private LocalDate submissionDate;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, int customerId, LocalDate submissionDate) {
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

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

}
