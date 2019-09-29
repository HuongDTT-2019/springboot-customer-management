package com.codegym.demothymleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerForm implements Validator {
    private Long id;
    @NotBlank
    private String name;
    private MultipartFile avatar;
    private Province province;
    @NotNull
    private LocalDate birthDate;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerForm customerForm = (CustomerForm)target;
        MultipartFile file = customerForm.getAvatar();
        if(file.getSize() == 0){
            errors.rejectValue("file", "upload.file.required");
        }
    }
}
