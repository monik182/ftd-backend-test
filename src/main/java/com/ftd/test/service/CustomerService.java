package com.ftd.test.service;

import com.ftd.test.model.Customer;
import com.ftd.test.model.Login;
import com.ftd.test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Object> addCustomer(Customer c) {
        String password = passwordEncoder.encode(c.getPassword());
//        System.out.println("PASSWORD =======>>>>>> " + password);
        Customer customer = new Customer(null, c.getFullName(), c.getBirthDate(), c.getDocumentId(), c.getEmail(), password, c.getUserName());
        customerRepository.save(customer);
        return new ResponseEntity<>("Cliente creado satisfactoriamente", HttpStatus.OK);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll()
                .forEach(customers::add);
        return customers;
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByUserName(String userName) {
        return customerRepository.findCustomerByUserName(userName);
    }

    public ResponseEntity<Object> loginByUserName(Login customerLoginData) {
        Customer dbCustomer = getCustomerByUserName(customerLoginData.getUserName()).orElse(null);

        if (dbCustomer == null || !passwordEncoder.matches(customerLoginData.getPassword(), dbCustomer.getPassword())) {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(dbCustomer, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteCustomerById(UUID id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Cliente eliminado satisfactoriamente", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateCustomerById(UUID id, Customer c) {
        Customer dbCustomer = getCustomerById(id).orElse(null);

        if (dbCustomer == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);
        }

        String password = dbCustomer.getPassword();
        if (!passwordEncoder.matches(c.getPassword(), password)) {
            password = passwordEncoder.encode(c.getPassword());
        }

        Customer customer = new Customer(id, c.getFullName(), c.getBirthDate(), c.getDocumentId(), c.getEmail(), password, c.getUserName());
        customerRepository.save(customer);
        return new ResponseEntity<>("Cliente editado satisfactoriamente", HttpStatus.OK);
    }

}
