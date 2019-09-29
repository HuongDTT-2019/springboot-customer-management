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
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.ResourceUtils.*;

@Service
@PropertySource("classpath:application.properties")
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Value(value = "${file-upload}")
    private String name;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(CustomerForm customerForm) {

        MultipartFile multipartFile = customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = name;

        // luu file len server
        try {
            FileCopyUtils.copy(customerForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getBirthDate(), fileName, customerForm.getProvince());
        customerRepository.save(customer);
    }

    @Override
    public void edit(CustomerForm customerForm) {
        Optional<Customer> customer = customerRepository.findById(customerForm.getId());
        MultipartFile multipartFile = customerForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        if (Objects.equals(fileName, "")) {
            Customer customerEdit = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getBirthDate(), customer.get().getAvatar(), customerForm.getProvince());
            customerRepository.save(customerEdit);
        } else {
            Customer customerEdit = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getBirthDate(), fileName, customerForm.getProvince());
            customerRepository.save(customerEdit);
            String fileUpload = name;

            // luu file len server
            try {
                FileCopyUtils.copy(customerForm.getAvatar().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        String pathFile = name+customer.get().getAvatar();
        try {
            File avatar = getFile(pathFile);
            FileUtils.forceDelete(avatar);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerRepository.deleteById(id);
    }
}
