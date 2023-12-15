package ru.eosreign.taskmanagementsystem.mapper;

import ru.eosreign.taskmanagementsystem.dto.NewTaskDto;
import ru.eosreign.taskmanagementsystem.dto.TaskDto;

public class NewTaskDtoMapper {
    public static TaskDto toTaskDto(NewTaskDto input) {
        TaskDto output = new TaskDto();
        output.setHeader(input.getHeader());
        output.setDescription(input.getDescription());
        output.setStatus(input.getStatus());
        output.setPriority(input.getPriority());
        output.setAuthor(input.getAuthor());
        return output;
    }
}
