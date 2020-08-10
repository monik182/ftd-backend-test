package com.ftd.test.repository;

import com.ftd.test.model.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StoreRepository extends CrudRepository<Store, UUID> {
}
