package com.ftd.test.model;

import java.util.UUID;

public class CartProduct {

    private UUID productId;

    private int quantity;

    private String name;

    private String description;

    private String image;

    private int stock;

    private int price;

    private long barcode;

    public CartProduct() {}

    public CartProduct(UUID productId,
                       int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartProduct(UUID productId,
                       String name,
                       String description,
                       String image,
                       int price,
                       int stock,
                       int quantity,
                       long barcode) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.barcode = barcode;
        this.image = image;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }
}
