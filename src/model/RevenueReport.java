package model;

import java.math.BigDecimal;

public class RevenueReport {
    private String label;
    private BigDecimal revenue;

    public RevenueReport() {
    }

    public RevenueReport(String label, BigDecimal revenue) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}
