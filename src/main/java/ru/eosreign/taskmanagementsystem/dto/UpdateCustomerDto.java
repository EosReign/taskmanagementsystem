package ru.eosreign.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerDto {
    private String fio;
    private String email;
    private Long authorityId;
    private LocalDateTime createdAt;
}
