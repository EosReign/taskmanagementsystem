package ru.eosreign.taskmanagementsystem.mapper;

import ru.eosreign.taskmanagementsystem.dto.TaskDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateTaskDto;

public class UpdateTaskDtoMapper {
    public static TaskDto toTaskDto(UpdateTaskDto input) {
        TaskDto output = new TaskDto();
        output.setHeader(input.getHeader());
        output.setDescription(input.getDescription());
        output.setStatus(input.getStatus());
        output.setPriority(input.getPriority());
        output.setAuthor(input.getAuthor());
        output.setExecutor(input.getExecutor());
        return output;
    }
}
