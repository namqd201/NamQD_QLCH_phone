package model;

import java.math.BigDecimal;

public class Product {
    private long productId;
    private String productName;
    private String brand;
    private BigDecimal productPrice;
    private int stock;

    public Product() {
    }

    public Product(long productId, String productName, String brand, BigDecimal productPrice, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.productPrice = productPrice;
        this.stock = stock;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
