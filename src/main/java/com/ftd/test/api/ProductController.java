package com.ftd.test.api;

import com.ftd.test.model.Product;
import com.ftd.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @NotNull @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping(path = "/batch")
    public ResponseEntity<Object> createProducts(@Valid @NotNull @RequestBody List<Product> products) {
        return productService.createProducts(products);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "{id}")
    public Optional<Product> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @PostMapping(path = "/byIds")
    public List<Product> getAllProductsById(@Valid @NotNull @RequestBody List<UUID> ids) {
        return productService.getAllProductsById(ids);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable UUID id) {
        return productService.deleteProductById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable UUID id, @Valid @NotNull @RequestBody Product product) {
        return productService.updateProductById(id, product);
    }
}
