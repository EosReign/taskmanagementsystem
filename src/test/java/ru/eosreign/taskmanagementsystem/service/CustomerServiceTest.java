package ru.eosreign.taskmanagementsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private AuthorityDao authorityDao;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void getCustomer(Long id) {

    }

    @Test
    public void getCustomers() {

    }

    @Test
    public void getCustomerByEmail(String email) {

    }

    @Test
    public void updateCustomer(CustomerDto dto, Long id) {

    }

    @Test
    public void deleteCustomer(Long id) {

    }

    @Test
    public void loadUserByUsername(String username) {

    }


}
