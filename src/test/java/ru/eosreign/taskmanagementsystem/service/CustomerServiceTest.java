package ru.eosreign.taskmanagementsystem.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eosreign.taskmanagementsystem.dao.AuthorityDao;
import ru.eosreign.taskmanagementsystem.dao.CustomerDao;
import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.ListCustomerDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCustomerDto;
import ru.eosreign.taskmanagementsystem.entity.Authority;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.exception.CustomerNotFoundException;
import ru.eosreign.taskmanagementsystem.exception.CustomerTableIsEmptyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private AuthorityDao authorityDao;

    @InjectMocks
    private CustomerService customerService;

    private CustomerDto testCustomerDto;
    private UpdateCustomerDto testUpdateCustomerDto;

    private Customer testCustomer;

    @BeforeEach
    public void init() {
        testCustomerDto = methodReturnTestCustomerDto();
        testUpdateCustomerDto = methodReturnTestUpdateCustomerDto();
        testCustomer = methodReturnTestCustomer();
    }

    @AfterEach
    public void teardown() {
        testCustomerDto = null;
        testUpdateCustomerDto = null;
        testCustomer = null;
    }

    @Test
    public void getCustomer_ReturnCustomerDto() {
        Mockito.when(customerDao.getCustomer(1L))
                .thenReturn(Optional.of(testCustomerDto));

        assertThat(testCustomerDto).isEqualTo(customerService.getCustomer(1L));
    }

    @Test
    public void getCustomer_ThrownException() {
        Mockito.when(customerDao.getCustomer(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.getCustomer(1L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("Customer with id: " + 1L + " not found");
    }

    @Test
    public void getCustomers_ReturnListCustomerDto() {
        List<CustomerDto> list = new ArrayList<>();
        list.add(testCustomerDto);
        ListCustomerDto listResponse = new ListCustomerDto(list);

        Mockito.when(customerDao.getCustomers())
                .thenReturn(Optional.of(list));

        assertThat(listResponse).isEqualTo(customerService.getCustomers());
    }

    @Test
    public void getCustomers_ThrownException() {
        Mockito.when(customerDao.getCustomers())
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.getCustomers())
                .isInstanceOf(CustomerTableIsEmptyException.class)
                .hasMessageContaining("Customer table is empty");
    }

    @Test
    public void getCustomerByEmail_ReturnCustomer() {
        Mockito.when(customerDao.getCustomerByEmail("test@gmail.com"))
                .thenReturn(Optional.of(testCustomer));

        assertThat(testCustomer).isEqualTo(customerService.getCustomerByEmail("test@gmail.com"));
    }

    @Test
    public void getCustomerByEmail_ThrownException() {
        Mockito.when(customerDao.getCustomerByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.getCustomerByEmail("test@gmail.com"))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("Customer with " + "test@gmail.com" + " not found");
    }

    @Test
    public void updateCustomer_ReturnCustomerDto() {
        Mockito.when(customerDao.getCustomer(1L))
                .thenReturn(Optional.of(testCustomerDto));

        assertThat(testCustomerDto).isEqualTo(customerService.updateCustomer(testUpdateCustomerDto, 1L));
    }

    @Test
    public void updateCustomer_ThrownException() {
        Mockito.when(customerDao.getCustomer(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.updateCustomer(testUpdateCustomerDto, 1L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("Customer with id: " + 1L + " not found");
    }

    @Test
    public void deleteCustomer_ReturnCustomerDto() {
        Mockito.when(customerDao.getCustomer(1L))
                .thenReturn(Optional.of(testCustomerDto));

        assertThat(testCustomerDto).isEqualTo(customerService.deleteCustomer(1L));
    }

    @Test
    public void deleteCustomer_ThrownException() {
        Mockito.when(customerDao.getCustomer(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.deleteCustomer(1L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("Customer with id: " + 1L + " not found");
    }

    private CustomerDto methodReturnTestCustomerDto() {
        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setEmail("test@gmail.com");
        dto.setFio("Test Testovich Testech");
        dto.setAuthorityId(1L);
        dto.setCreatedAt(LocalDateTime.now());
        return dto;
    }

    private UpdateCustomerDto methodReturnTestUpdateCustomerDto() {
        UpdateCustomerDto dto = new UpdateCustomerDto();
        dto.setEmail("test@gmail.com");
        dto.setFio("Test Testovich Testech");
        dto.setAuthorityId(1L);
        dto.setCreatedAt(LocalDateTime.now());
        return dto;
    }

    private Customer methodReturnTestCustomer() {
        Customer entity = new Customer();
        entity.setId(1L);
        entity.setFio("Test Testovich Testech");
        entity.setPassword("password");
        entity.setEmail("test@gmail.com");
        entity.setAuthority(new Authority(1L, "USER"));
        entity.setCreatedAt(LocalDate.now());
        return entity;

    }



}
