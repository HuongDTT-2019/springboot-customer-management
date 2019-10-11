package com.codegym.demothymleaf.controller;

import com.codegym.demothymleaf.model.CounterAccess;
import com.codegym.demothymleaf.model.Customer;
import com.codegym.demothymleaf.model.CustomerForm;
import com.codegym.demothymleaf.model.Province;
import com.codegym.demothymleaf.services.CustomerService;
import com.codegym.demothymleaf.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@SessionAttributes("counterAccess")
public class CustomerController {

    private CustomerService customerService;
    private ProvinceService provinceService;

    @Autowired
    public CustomerController(CustomerService customerService, ProvinceService provinceService) {
        this.provinceService = provinceService;
        this.customerService = customerService;
    }
    @ModelAttribute("counterAccess")
    public CounterAccess setUpCounter() {
        return new CounterAccess();
    }

    @ModelAttribute("provinces")
    public Iterable<Province> listProvince() {
        return provinceService.findAll();
    }

    @GetMapping(value = "create")
    public String addCustomer(@ModelAttribute("counterAccess") CounterAccess counterAccess, Model model) {
        counterAccess.increment();
        model.addAttribute("customer", new CustomerForm());
        return "index";
    }

    @PostMapping(value = "create")
    public ModelAndView createCustomer(@Valid @ModelAttribute("customer") CustomerForm customerForm, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
                return new ModelAndView("index");
            }
            customerService.save(customerForm);
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("message", "thanh cong");
            return modelAndView;
    }

    @GetMapping(value = "list")
    public ModelAndView listCustomer() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customer", customers);
        return modelAndView;
    }

    @GetMapping(value = "edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            CustomerForm customerForm = new CustomerForm(customer.get().getId(), customer.get().getName(),null,customer.get().getProvince(),customer.get().getBirthDate());
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customerForm", customerForm);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }
        return new ModelAndView("error404");

    }

    @PostMapping(value = "edit")
    public ModelAndView editCustomer(@ModelAttribute("customerForm") CustomerForm customerForm, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println("Result Error Occured" + bindingResult.getAllErrors());
        }
        Optional<Customer> customer = customerService.findById(customerForm.getId());
        customerService.edit(customerForm);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "edit success");
        return modelAndView;
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("customer/delete");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }
        return new ModelAndView("error404");
    }
    @PostMapping(value = "delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) throws IOException {
        customerService.remove(customer.getId());
        return "redirect:list";
    }
    @RequestMapping(value = "search")
    public ModelAndView searchNameCustomer(@RequestParam("name") String name){
        Iterable<Customer> customersFind = customerService.findCustomerByName(name);
        ModelAndView modelAndView = new ModelAndView("customer/searchName");
        modelAndView.addObject("customer",customersFind);
        return modelAndView;
    }

}
