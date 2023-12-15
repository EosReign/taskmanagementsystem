package ru.eosreign.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;
    private String fio;
    private String email;
    private String password;
    private Authority authority;
    private LocalDate createdAt;
}
