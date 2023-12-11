package ru.eosreign.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eosreign.taskmanagementsystem.service.CommentService;


@Tag(name = "comments", description = "The comment API")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    public CommentService service;
    public CommentController(CommentService service) {
        this.service = service;
    }


}
