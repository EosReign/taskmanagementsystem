package ru.eosreign.taskmanagementsystem.mapper;

import ru.eosreign.taskmanagementsystem.dto.CustomerDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCustomerDto;

public class UpdateCustomerDtoMapper {
    public static CustomerDto toCustomerDto(UpdateCustomerDto input) {
        CustomerDto output = new CustomerDto();
        output.setFio(input.getFio());
        output.setEmail(input.getEmail());
        output.setAuthorityId(input.getAuthorityId());
        output.setCreatedAt(input.getCreatedAt());
        return output;
    }
}
