package ru.eosreign.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.service.CustomerService;



@RestController
@RequestMapping("/auth")
public class AuthController {
    private CustomerService service;

    public AuthController(CustomerService service) {
        this.service = service;
    }


}
