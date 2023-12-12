package ru.eosreign.taskmanagementsystem.mapper;

import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.NewCustomerDto;

public class NewCustomerDtoMapper {
    public static CustomerDto toCustomerDto(NewCustomerDto input) {
        CustomerDto output = new CustomerDto();
        output.setFio(input.getFio());
        output.setEmail(input.getEmail());
        return output;
    }
}
