package ru.eosreign.taskmanagementsystem.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.ListCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Customer;

import java.util.HashSet;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    private final AuthorityDao authorityDao;
    public CustomerService(CustomerDao customerDao,
                           AuthorityDao authorityDao) {
        this.customerDao = customerDao;
        this.authorityDao = authorityDao;
    }

    public NewCustomerDto createCustomer(NewCustomerDto dto) {
        customerDao.createCustomer(dto, authorityDao.createAuthority("User"));
        return dto;
    }

    public CustomerDto getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    public ListCustomerDto getCustomers() {
        return new ListCustomerDto(customerDao.getCustomers());
    }

    public CustomerDto updateCustomer(CustomerDto dto, Long id) {
        customerDao.updateCustomer(dto, id);
        return dto;
    }

    public void deleteCustomer(Long id) {
        customerDao.deleteCustomer(id);
    }

}
