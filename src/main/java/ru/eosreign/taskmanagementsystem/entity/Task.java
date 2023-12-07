package ru.eosreign.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long id;
    private String header;
    private String description;
    private String status;
    private String priority;
    private Long author;
    private Long executor;

}
