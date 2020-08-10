package com.ftd.test.repository;

import com.ftd.test.model.ProductsInStore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductsInStoreRepository extends CrudRepository<ProductsInStore, UUID> {

    Optional<List<ProductsInStore>> findAllByStoreId(UUID id);
}
