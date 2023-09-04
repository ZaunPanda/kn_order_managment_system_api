package kn.kn_order_managment_system_api.dto;


import kn.kn_order_managment_system_api.entity.Customer;

public class OrderDTO {

    private int orderId;
    private Customer customerId;
    private String submissionDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

}
