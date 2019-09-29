package com.codegym.demothymleaf.services;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;

import java.util.Optional;

public interface CustomerService {
    Iterable<Customer> findAll();
    void save(CustomerForm customerForm);
    void edit(CustomerForm customerForm);
    Optional<Customer> findById(Long id);
    void remove(Long id);

}
