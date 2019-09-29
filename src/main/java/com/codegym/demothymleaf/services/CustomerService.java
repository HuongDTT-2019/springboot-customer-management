package com.codegym.demothymleaf.services;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;

public interface CustomerService {
    Iterable<Customer> findAll();
    void save(CustomerForm customerForm);
}
