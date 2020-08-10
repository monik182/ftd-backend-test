package com.ftd.test.service;

import com.ftd.test.model.ProductsInStore;
import com.ftd.test.model.Store;
import com.ftd.test.repository.ProductsInStoreRepository;
import com.ftd.test.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private StoreRepository storeRepository;
    private ProductsInStoreRepository productsInStoreRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository,
                        ProductsInStoreRepository productsInStoreRepository) {
        this.storeRepository = storeRepository;
        this.productsInStoreRepository = productsInStoreRepository;
    }

    public ResponseEntity<Object> createStore(Store store) {
        storeRepository.save(store);
        return new ResponseEntity<>("Tienda guardada satisfactoriamente.", HttpStatus.OK);
    }

    public ResponseEntity<Object> createStores(List<Store> stores) {
        storeRepository.saveAll(stores);
        return new ResponseEntity<>("Tiendas guardadas satisfactoriamente.", HttpStatus.OK);
    }

    public List<Store> getAllStores() {
        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);
        return stores;
    }

    public Optional<Store> getStoreById(UUID id) {
        return storeRepository.findById(id);
    }

    public ResponseEntity<Object> deleteStoreById(UUID id) {
        storeRepository.deleteById(id);
        return new ResponseEntity<>("Tienda eliminada satisfactoriamente.", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateStoreById(UUID id, Store s) {
        Store store = new Store(id, s.getName(), s.getAddress(), s.getOpeningHour(), s.getClosingHour());
        storeRepository.save(store);
        return new ResponseEntity<>("Tienda actualizada satisfactoriamente.", HttpStatus.OK);
    }

    public ResponseEntity<Object> addProductsToStores(UUID id, List<ProductsInStore> products) {

        for (ProductsInStore p : products) {
            p.setStoreId(id);
        }
        productsInStoreRepository.saveAll(products);
        return new ResponseEntity<>("Productos guardados satisfactoriamente en la tienda.", HttpStatus.OK);
    }

    public ResponseEntity<Object> getProductsInStores(UUID id) {
        List<ProductsInStore> products = productsInStoreRepository.findAllByStoreId(id).orElse(null);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
