package com.ftd.test.repository;

import com.ftd.test.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    Optional<Customer> findCustomerByUserName(String userName);

}
