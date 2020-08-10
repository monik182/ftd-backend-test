package com.ftd.test.repository;

import com.ftd.test.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart, UUID> {

    Optional<Cart> findCartByCustomerId(UUID id);

    void deleteAllByCustomerId(UUID id);

    Optional<List<Cart>> findAllByCustomerId(UUID id);

    Optional<List<Cart>> findAllByCustomerIdAndQuantityGreaterThan(UUID id, int quantity);
}
