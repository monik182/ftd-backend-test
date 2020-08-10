package com.ftd.test.api;

import com.ftd.test.model.Cart;
import com.ftd.test.model.CustomerCart;
import com.ftd.test.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping("api/v1/cart")
@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Object> createCart(@Valid @NotNull @RequestBody CustomerCart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping(path = "/customer/{id}")
    public CustomerCart getCartByCustomerId(@PathVariable UUID id) {
        return cartService.getCartByCustomerId(id);
    }

    @DeleteMapping(path = "/customer/{id}")
    public ResponseEntity<Object> deleteCartByCustomerId(@PathVariable UUID id) {
        return cartService.deleteCartByCustomerId(id);
    }

    @PutMapping(path = "/customer/{id}")
    public ResponseEntity<Object> updateCartByCustomerId(@PathVariable UUID id, @Valid @NotNull @RequestBody CustomerCart cart) {
        return cartService.updateCartByCustomerId(id, cart);
    }
}
