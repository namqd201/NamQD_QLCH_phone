package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice {
    private int id;
    private int customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;

    private List<InvoiceDetail> details;

    public Invoice() {
    }

    public Invoice(int id, int customerId, LocalDateTime createdAt, BigDecimal totalAmount,  List<InvoiceDetail> details) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }
}
