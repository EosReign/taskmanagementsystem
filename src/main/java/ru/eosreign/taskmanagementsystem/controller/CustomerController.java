package ru.eosreign.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eosreign.taskmanagementsystem.dto.ListCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.service.CustomerService;

@Tag(name = "customers", description = "The customer API")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    public CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found customer"
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> readCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Get customers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found customers",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                                    )
                            }
                    )

            })
    @GetMapping()
    public ResponseEntity<ListCustomerDto> readCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.FOUND);
    }

    @Operation(summary = "Update customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "customer is updated",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody UpdateCustomerDto dto,
                                                            @PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.updateCustomer(dto, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "customer is deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }


}
