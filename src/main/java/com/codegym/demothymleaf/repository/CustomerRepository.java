package com.codegym.demothymleaf.repository;

import com.codegym.demothymleaf.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
}
