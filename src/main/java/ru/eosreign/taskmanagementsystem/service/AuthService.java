package ru.eosreign.taskmanagementsystem.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.JwtRequest;
import ru.eosreign.taskmanagementsystem.dto.JwtResponse;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.exception.AuthorityNotFoundException;
import ru.eosreign.taskmanagementsystem.exception.CreatingCustomerIsFailedException;
import ru.eosreign.taskmanagementsystem.exception.CustomerNotFoundException;
import ru.eosreign.taskmanagementsystem.utils.JwtTokenUtils;

@Service
public class AuthService {
    private final CustomerService service;

    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final CustomerDao customerDao;

    private final AuthorityDao authorityDao;
    public AuthService(CustomerDao customerDao,
                       AuthorityDao authorityDao,
                       JwtTokenUtils jwtTokenUtils,
                       AuthenticationManager authenticationManager,
                       CustomerService service) {
        this.customerDao = customerDao;
        this.authorityDao = authorityDao;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.service = service;
    }

    public CustomerDto createCustomer(NewCustomerDto dto) throws AuthorityNotFoundException {
        try {
            Long id = customerDao.createCustomer(dto, authorityDao.createAuthority("USER")
                            .orElseThrow(() -> new AuthorityNotFoundException("Creating authority is failed")))
                    .orElseThrow(() -> new CreatingCustomerIsFailedException("Creating customer is failed"));
            CustomerDto response = customerDao.getCustomer(id)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found after creating"));
            response.setId(id);
            return response;

        } catch (AuthorityNotFoundException e) {
            throw new AuthorityNotFoundException(e.getMessage());
        } catch (CreatingCustomerIsFailedException e) {
            throw new CreatingCustomerIsFailedException(e.getMessage());
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException(e.getMessage());
        }

    }

    public JwtResponse createAuthToken(JwtRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        UserDetails userDetails = service.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
