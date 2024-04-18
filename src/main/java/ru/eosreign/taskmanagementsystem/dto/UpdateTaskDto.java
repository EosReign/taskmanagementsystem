package ru.eosreign.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDto {

    private String header;
    private String description;
    private String status;
    private String priority;
    private Long author;
    private Long executor;
}
