package ru.eosreign.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eosreign.taskmanagementsystem.dto.*;
import ru.eosreign.taskmanagementsystem.entity.Customer;
import ru.eosreign.taskmanagementsystem.service.CommentService;


@Tag(name = "comments", description = "The comment API")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    public CommentService service;
    public CommentController(CommentService service) {
        this.service = service;
    }

    @Operation(summary = "Create comment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment is created",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@RequestBody NewCommentDto dto) {
        return new ResponseEntity<>(service.createComment(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get comment by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> readComment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getComment(id), HttpStatus.FOUND);
    }

    @Operation(summary = "Get comments",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found comments",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                                    )
                            }
                    )

            })
    @GetMapping()
    public ResponseEntity<ListCommentDto> readComments() {
        return new ResponseEntity<>(service.getComments(), HttpStatus.FOUND);
    }

    @Operation(summary = "Update comment by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment is updated",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateTask(@RequestBody UpdateCommentDto dto,
                                              @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.updateComment(dto, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete comment by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment is deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    )

            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
