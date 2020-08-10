package com.ftd.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Cart {

    @Id
    @NotNull(message = "El campo no debe ser nulo")
    private UUID id;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID cartId;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID storeId;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID customerId;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID productId;

    @NotNull(message = "El campo no debe ser nulo")
    private int quantity;

    public Cart () { }

    public Cart (@JsonProperty("id") UUID id,
                 @JsonProperty("cartId") UUID cartId,
                 @JsonProperty("storeId") UUID storeId,
                 @JsonProperty("customerId") UUID customerId,
                 @JsonProperty("productId") UUID productId,
                 @JsonProperty("quantity") int quantity) {
        this.id = id != null ? id : this.generateId();
        this.cartId = cartId;
        this.storeId = storeId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public UUID getCartId() { return cartId; }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(UUID id) {
        this.id = id != null ? id : UUID.randomUUID();
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID generateId() {
        return UUID.randomUUID();
    }
}
