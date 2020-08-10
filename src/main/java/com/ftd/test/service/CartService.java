package com.ftd.test.service;

import com.ftd.test.model.*;
import com.ftd.test.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    private CartRepository cartRepository;
    private ProductService productService;
    private StoreService storeService;

    @Autowired
    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       StoreService storeService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.storeService = storeService;
    }

    public ResponseEntity<Object> createCart(CustomerCart cart) {
        CustomerCart customerCart = getCartByCustomerId(cart.getCustomerId());
        if (customerCart != null) {
            return new ResponseEntity<>("Ya tiene un carrito activo.", HttpStatus.CONFLICT);
        }

        Optional<Store> store = storeService.getStoreById(cart.getStoreId());
        if (store.isEmpty()) {
            return new ResponseEntity<>("La tienda no esta activa.", HttpStatus.BAD_REQUEST);
        }

        List<UUID> productIds = new ArrayList<>();
        cart.getProductList().forEach(p -> { productIds.add(p.getProductId()); });
        List<Product> products = productService.getAllProductsById(productIds);

        for (Product p : products) {
            if (cart.getProductList().stream().filter(pi -> pi.getProductId().equals(p.getId())).findFirst().orElse(null) == null) {
                return new ResponseEntity<>("Producto no encontrado.", HttpStatus.BAD_REQUEST);
            }
        }

        UUID cartId = UUID.randomUUID();
        List<Cart> cartList = new ArrayList<>();
        cart.getProductList().forEach(product -> {
            cartList.add(new Cart(null, cartId, cart.getStoreId(), cart.getCustomerId(), product.getProductId(), product.getQuantity()));
        });
        cartRepository.saveAll(cartList);
        return new ResponseEntity<>(getCartByCustomerId(cart.getCustomerId()), HttpStatus.OK);
    }

    public List<Cart> findAllByCustomerId(UUID id) {
        return cartRepository.findAllByCustomerId(id).orElse(null);
    }

    public List<Cart> findAllByCustomerId(UUID id, int quantity) {
        return cartRepository.findAllByCustomerIdAndQuantityGreaterThan(id, quantity).orElse(null);
    }

    public CustomerCart getCartByCustomerId(UUID id) {
        List<Cart> cartList = findAllByCustomerId(id, 0);
        CustomerCart customerCart = null;

        if (cartList != null) {
            Cart c = cartList.get(0);
            List<CartProduct> productList = new ArrayList<>();
            List<UUID> productIds = new ArrayList<>();
            cartList.forEach(cart -> {
                productIds.add(cart.getProductId());
                productList.add(new CartProduct(cart.getProductId(), cart.getQuantity()));
            });
            List<Product> products = productService.getAllProductsById(productIds);
            productList.forEach(p -> {
                Product product = products.stream().filter(prod -> prod.getId().equals(p.getProductId())).findFirst().orElse(null);
                if (product != null) {
                    p.setBarcode(product.getBarcode());
                    p.setDescription(product.getDescription());
                    p.setName(product.getName());
                    p.setImage(product.getImage());
                    p.setStock(product.getStock());
                    p.setPrice(product.getPrice());
                }
            });
            customerCart = new CustomerCart(c.getCartId(), c.getStoreId(), c.getCustomerId(), productList);
        }
//        return cartRepository.findAllByCustomerId(id);
        return customerCart;
    }

    public ResponseEntity<Object> deleteCartByCustomerId(UUID id) {
        cartRepository.deleteAllByCustomerId(id);
        return new ResponseEntity<>("Carrito eliminado satisfactoriamente.", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateCartByCustomerId(UUID id, CustomerCart cart) {
        List<Cart> cartList = new ArrayList<>();
        List<Cart> customerCart = findAllByCustomerId(id);

        cart.getProductList().forEach(product -> {
            Cart c = customerCart.stream().filter(ci -> ci.getProductId().equals(product.getProductId())).findFirst().orElse(null);

            Product prod = productService.getProductById(product.getProductId()).orElse(null);
            if (prod != null) {
                if (c != null) {
                    c.setQuantity(product.getQuantity());
                } else {
                    c = new Cart();
                    c.setId(null);
                    c.setQuantity(product.getQuantity());
                    c.setProductId(product.getProductId());
                    c.setCartId(cart.getCartId());
                    c.setStoreId(cart.getStoreId());
                    c.setCustomerId(cart.getCustomerId());
                }
            }

            cartList.add(c);

        });
        cartRepository.saveAll(cartList);

        return new ResponseEntity<>(getCartByCustomerId(id), HttpStatus.OK);
    }

}
