package com.codegym.demothymleaf.services.impl;

import com.codegym.demothymleaf.model.Province;
import com.codegym.demothymleaf.repository.ProvinceRepository;
import com.codegym.demothymleaf.services.ProvinceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceRepository provinceRepository;
    public ProvinceServiceImpl(ProvinceRepository provinceRepository){
        this.provinceRepository = provinceRepository;
    }
    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

}
