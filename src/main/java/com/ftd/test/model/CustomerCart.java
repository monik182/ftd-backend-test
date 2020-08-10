package com.ftd.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class CustomerCart {

    private UUID cartId;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID storeId;

    @NotNull(message = "El campo no debe ser nulo")
    private UUID customerId;

    @NotNull(message = "El campo no debe ser nulo")
    private List<CartProduct> productList;

    private long total;

    public CustomerCart() { }

    public CustomerCart(@JsonProperty("cartId") UUID cartId,
                        @JsonProperty("storeId") UUID storeId,
                        @JsonProperty("customerId") UUID customerId,
                        @JsonProperty("productList") List<CartProduct> productList) {
        this.cartId = cartId;
        this.storeId = storeId;
        this.customerId = customerId;
        this.productList = productList;
        this.total = this.calculateTotal();
    }

    public UUID getStoreId() {
        return storeId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getCartId() {
        return cartId;
    }

    public List<CartProduct> getProductList() {
        return productList;
    }

    public long getTotal() {
        return total;
    }

    private long calculateTotal() {
        for (CartProduct product : this.getProductList()) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
}
