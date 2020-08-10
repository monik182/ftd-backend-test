package com.ftd.test.service;

import com.ftd.test.model.Product;
import com.ftd.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Object> createProduct(Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("Producto guardado satisfactoriamente.", HttpStatus.OK);
    }

    public ResponseEntity<Object> createProducts(List<Product> products) {
        productRepository.saveAll(products);
        return new ResponseEntity<>("Productos guardados satisfactoriamente.", HttpStatus.OK);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository
                .findAll()
                .forEach(products::add);
        return products;
    }

    public List<Product> getAllProductsById(List<UUID> ids) {
        return productRepository.findByIdIn(ids);
    }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<Object> deleteProductById(UUID id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Producto eliminado satisfactoriamente.", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateProductById(UUID id, Product p) {
        Product product = new Product(id, p.getName(), p.getDescription(), p.getPrice(), p.getStock(), p.getBarcode());
        productRepository.save(product);
        return new ResponseEntity<>("Producto actualizado satisfactoriamente.", HttpStatus.OK);
    }

}
