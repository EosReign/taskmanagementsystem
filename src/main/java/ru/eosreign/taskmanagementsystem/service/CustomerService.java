package ru.eosreign.taskmanagementsystem.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.ListCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Customer;

import java.util.*;

import static java.util.Arrays.stream;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerDao customerDao;

    private final AuthorityDao authorityDao;
    public CustomerService(CustomerDao customerDao,
                           AuthorityDao authorityDao) {
        this.customerDao = customerDao;
        this.authorityDao = authorityDao;
    }

    public CustomerDto getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    public ListCustomerDto getCustomers() {
        return new ListCustomerDto(customerDao.getCustomers());
    }
    public Customer getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }

    public CustomerDto updateCustomer(CustomerDto dto, Long id) {
        customerDao.updateCustomer(dto, id);
        return dto;
    }

    public void deleteCustomer(Long id) {
        customerDao.deleteCustomer(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerDao.getCustomerByEmail(username);

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(customer.getAuthority().getRole()));

        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                roles
        );
    }



}
