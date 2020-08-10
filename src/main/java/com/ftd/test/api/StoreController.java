package com.ftd.test.api;

import com.ftd.test.model.ProductsInStore;
import com.ftd.test.model.Store;
import com.ftd.test.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/store")
@RestController
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<Object> createStore(@Valid @NotNull @RequestBody Store store) {
        return storeService.createStore(store);
    }

    @PostMapping(path = "/batch")
    public ResponseEntity<Object> createStores(@Valid @NotNull @RequestBody List<Store> stores) {
        return storeService.createStores(stores);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping(path = "{id}")
    public Optional<Store> getStoreById(@PathVariable UUID id) {
        return storeService.getStoreById(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteStoreById(@PathVariable UUID id) {
        return storeService.deleteStoreById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateStoreById(@PathVariable UUID id, @Valid @NotNull @RequestBody Store store) {
        return storeService.updateStoreById(id, store);
    }

    @PostMapping(path = "/products/{id}")
    public ResponseEntity<Object> addProductsToStores(@PathVariable UUID id, @Valid @NotNull @RequestBody List<ProductsInStore> products) {
        return storeService.addProductsToStores(id, products);
    }

    @GetMapping(path = "products/{id}")
    public ResponseEntity<Object> getProductsInStores(@PathVariable UUID id) {
        return storeService.getProductsInStores(id);
    }
}
