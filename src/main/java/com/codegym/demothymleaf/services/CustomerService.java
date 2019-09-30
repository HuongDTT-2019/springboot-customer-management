package com.codegym.demothymleaf.services;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public interface CustomerService {
    Iterable<Customer> findAll();
    void save(CustomerForm customerForm);
    void edit(CustomerForm customerForm) throws IOException;
    Optional<Customer> findById(Long id);
    void remove(Long id) throws IOException;

}
