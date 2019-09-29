package com.codegym.demothymleaf.repository;

import com.codegym.demothymleaf.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends PagingAndSortingRepository<Province,Long> {
}
