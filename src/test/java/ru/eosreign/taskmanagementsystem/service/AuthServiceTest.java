package ru.eosreign.taskmanagementsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.JwtRequest;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.utils.JwtTokenUtils;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    public CustomerService service;

    @Mock
    public JwtTokenUtils jwtTokenUtils;

    @Mock
    public AuthenticationManager authenticationManager;

    @Mock
    public CustomerDao customerDao;

    @Mock
    public AuthorityDao authorityDao;

    @InjectMocks
    public AuthService authService;


    @Test
    public void createCustomer_(NewCustomerDto dto) {

    }


    @Test
    public void createAuthToken_(JwtRequest authRequest) {

    }


}
