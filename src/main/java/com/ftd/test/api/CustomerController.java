package com.ftd.test.api;

import com.ftd.test.model.Customer;
import com.ftd.test.model.Login;
import com.ftd.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Object> addCustomer(@Valid @NotNull @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "{id}")
    public Optional<Customer> getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping(path = "/username/{userName}")
    public Customer getCustomerById(@PathVariable String userName) {
        return customerService.getCustomerByUserName(userName).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable UUID id) {
        return customerService.deleteCustomerById(id);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable UUID id, @Valid @NotNull @RequestBody Customer customer) {
        return customerService.updateCustomerById(id, customer);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @NotNull @RequestBody Login customer) {
        return customerService.loginByUserName(customer);
    }
}
