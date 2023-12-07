package ru.eosreign.taskmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCustomerDto;
import ru.eosreign.taskmanagementsystem.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    public CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<Void> createCustomer(@RequestBody NewCustomerDto dto) {
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Void> readCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@RequestBody UpdateCustomerDto dto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.updateCustomer(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
