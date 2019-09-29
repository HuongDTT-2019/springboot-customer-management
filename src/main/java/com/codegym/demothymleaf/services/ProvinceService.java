package com.codegym.demothymleaf.services;

import com.codegym.demothymleaf.model.Province;

import java.util.Optional;

public interface ProvinceService {
    Iterable<Province> findAll();
    Optional<Province> findById(Long id);
}
