package ru.eosreign.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.JwtRequest;
import ru.eosreign.taskmanagementsystem.dto.JwtResponse;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
    @Operation(summary = "Login customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer is authenticated",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )
            })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody JwtRequest authRequest) {
        return new ResponseEntity<>(service.createAuthToken(authRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Create customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer is created",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )
            })
    @PostMapping("/register")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody NewCustomerDto dto) {
        return new ResponseEntity<>(service.createCustomer(dto), HttpStatus.CREATED);
    }

}
