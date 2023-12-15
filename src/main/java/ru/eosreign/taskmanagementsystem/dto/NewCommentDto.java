package ru.eosreign.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentDto {
    private Long authorId;
    private Long taskId;
    private String text;
}
