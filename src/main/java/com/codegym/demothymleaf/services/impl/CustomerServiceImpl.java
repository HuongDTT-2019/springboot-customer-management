package com.codegym.demothymleaf.services.impl;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;
import com.codegym.demothymleaf.repository.CustomerRepository;
import com.codegym.demothymleaf.services.CustomerService;
import org.apache.catalina.webresources.FileResource;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.ResourceUtils.*;

@Service
@PropertySource("classpath:application.properties")
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private FileServiceImpl fileService;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,FileServiceImpl fileService) {
        this.customerRepository = customerRepository;
        this.fileService = fileService;
    }
    @Value(value = "${file-upload}")
    private String name;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(CustomerForm customerForm) {

        String fileUpload = name;
        MultipartFile multipartFile =customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        fileService.addFile(customerForm);
        Customer customer = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getBirthDate(), fileName, customerForm.getProvince());
        customerRepository.save(customer);
    }

    @Override
    public void edit(CustomerForm customerForm) throws IOException {
        Optional<Customer> customer = customerRepository.findById(customerForm.getId());
        MultipartFile multipartFile = customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        if (Objects.equals(fileName, "")) {
            Customer customerEdit = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getBirthDate(), customer.get().getAvatar(), customerForm.getProvince());
            customerRepository.save(customerEdit);
        } else {
            fileService.deleteFile(fileService.callFile(customer));
            Customer customerEdit = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getBirthDate(), fileName, customerForm.getProvince());
            customerRepository.save(customerEdit);
            fileService.addFile(customerForm);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void remove(Long id) throws IOException {
        Optional<Customer> customer = customerRepository.findById(id);
        fileService.deleteFile(fileService.callFile(customer));
        customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findCustomerByName(String name) {
        return customerRepository.findCustomerByName(name);
    }
}
