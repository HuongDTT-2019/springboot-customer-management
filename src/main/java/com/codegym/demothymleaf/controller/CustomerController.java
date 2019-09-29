package com.codegym.demothymleaf.controller;

import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;
import com.codegym.demothymleaf.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @GetMapping(name = "create")
    public String addCustomer(Model model){
        model.addAttribute("customer", new CustomerForm());
        return "index";
    }
    @PostMapping(name = "create")
    public ModelAndView modelAndView(@ModelAttribute("customer") CustomerForm customerForm, BindingResult bindingResult){
        customerService.save(customerForm);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "thanh cong");
        return modelAndView;
    }

}
