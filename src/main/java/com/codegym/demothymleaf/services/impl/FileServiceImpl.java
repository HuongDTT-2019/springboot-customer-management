package com.codegym.demothymleaf.services.impl;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;
import com.codegym.demothymleaf.services.CustomerService;
import com.codegym.demothymleaf.services.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.util.ResourceUtils.getFile;
@Service
@PropertySource("classpath:application.properties")
public class FileServiceImpl implements FileService {
    @Value(value = "${file-upload}")
    private String name;
    @Override
    public void addFile(CustomerForm customerForm) {
        MultipartFile multipartFile1 =customerForm.getAvatar();
        String fileName = multipartFile1.getOriginalFilename();
        String fileUpload = name;
        // luu file len server
        try {
            FileCopyUtils.copy(customerForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public File callFile(Optional<Customer> customer) throws FileNotFoundException {
        String pathFile = name + customer.get().getAvatar();
        File fileAvatar = getFile(pathFile);
        return fileAvatar;
    }

    @Override

    public void deleteFile(File file) throws IOException {
        FileUtils.forceDelete(file);
    }
}
