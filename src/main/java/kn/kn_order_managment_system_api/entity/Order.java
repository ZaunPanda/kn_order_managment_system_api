package kn.kn_order_managment_system_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @NotNull(message = "Customer ID cannot be empty")
    @JoinColumn(name = "customer_id")
    private Integer customerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "submission_date")
    private LocalDate submissionDate;

    public Order(int customerId, LocalDate  submissionDate) {
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

    public LocalDate  getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate  submissionDate) {
        this.submissionDate = submissionDate;
    }
}
