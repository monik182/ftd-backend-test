package com.ftd.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class ProductsInStore {

    @Id
    @NotNull(message = "El campo no debe ser nulo")
    private UUID id;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID storeId;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID productId;

    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    private int stock;

    public ProductsInStore() { }

    public ProductsInStore(@JsonProperty("id") UUID id,
                           @JsonProperty("productId") UUID productId,
                           @JsonProperty("stock") int stock) {
        this.id = id != null ? id : UUID.randomUUID();
        this.productId = productId;
        this.stock = stock;
    }

    public UUID getId() { return id; }

    public UUID getStoreId() {
        return storeId;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
