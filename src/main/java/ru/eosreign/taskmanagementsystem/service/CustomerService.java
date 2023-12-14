package ru.eosreign.taskmanagementsystem.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.ListCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.exception.CustomerNotFoundException;
import ru.eosreign.taskmanagementsystem.exception.CustomerTableIsEmptyException;
import ru.eosreign.taskmanagementsystem.mapper.UpdateCustomerDtoMapper;

import java.util.*;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerDao customerDao;

    private final AuthorityDao authorityDao;
    public CustomerService(CustomerDao customerDao,
                           AuthorityDao authorityDao) {
        this.customerDao = customerDao;
        this.authorityDao = authorityDao;
    }

    public CustomerDto getCustomer(Long id) throws CustomerNotFoundException {
        return customerDao.getCustomer(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " not found")
                );
    }

    public ListCustomerDto getCustomers() throws CustomerTableIsEmptyException {
        return new ListCustomerDto(customerDao.getCustomers()
                .orElseThrow(() -> new CustomerTableIsEmptyException("Customer table is empty")
                ));
    }
    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {
        return customerDao.getCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with " + email + " not found")
                );
    }

    //TODO обработка на сравнение до и после изменения
    public CustomerDto updateCustomer(UpdateCustomerDto request, Long id) throws CustomerNotFoundException {
        customerDao.getCustomer(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " not found"));
        CustomerDto response = UpdateCustomerDtoMapper.toCustomerDto(request);
        response.setId(id);
        customerDao.updateCustomer(request, id);
        return response;
    }

    public CustomerDto deleteCustomer(Long id) throws CustomerNotFoundException {
        CustomerDto dto = customerDao.getCustomer(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " not found"));
        customerDao.deleteCustomer(id);
        authorityDao.deleteAuthority(dto.getAuthorityId());
        return dto;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws CustomerNotFoundException {
        Customer customer = getCustomerByEmail(username);

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(customer.getAuthority().getRole()));

        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                roles
        );
    }



}
