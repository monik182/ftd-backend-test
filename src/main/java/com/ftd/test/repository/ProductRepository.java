package com.ftd.test.repository;

import com.ftd.test.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    List<Product> findByIdIn(List<UUID> id);
}
