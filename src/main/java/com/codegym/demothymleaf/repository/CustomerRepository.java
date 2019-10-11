package com.codegym.demothymleaf.repository;

import com.codegym.demothymleaf.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "select c from Customer c where c.name like %?1%")
    Iterable<Customer> findCustomerByName(String name);
}
