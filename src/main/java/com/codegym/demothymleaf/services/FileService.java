package com.codegym.demothymleaf.services;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public interface FileService {
    void addFile(CustomerForm customerForm);
    File callFile(Optional<Customer> customer) throws FileNotFoundException;
    void deleteFile(File file) throws IOException;
}
