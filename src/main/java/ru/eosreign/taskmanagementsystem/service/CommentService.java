package ru.eosreign.taskmanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.eosreign.taskmanagementsystem.dao.CommentDao;
import ru.eosreign.taskmanagementsystem.dto.*;
import ru.eosreign.taskmanagementsystem.exception.CommentNotFoundException;
import ru.eosreign.taskmanagementsystem.exception.CommentTableIsEmptyException;
import ru.eosreign.taskmanagementsystem.exception.CreatingCommentFailedException;

import java.time.LocalDate;


@Service
public class CommentService {
    private final CommentDao commentDao;
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    public CommentDto createComment(NewCommentDto dto) throws CreatingCommentFailedException {
        Long id = commentDao.createComment(dto)
                .orElseThrow(() -> new CreatingCommentFailedException("Creating comment was failed"));
        CommentDto commentDto = commentDao.getComment(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found by id:" + id));
        return commentDto;
    }

    public CommentDto getComment(Long id) throws CommentNotFoundException {
        return commentDao.getComment(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found by id:" + id)
                );
    }

    public ListCommentDto getComments() throws CommentTableIsEmptyException {
        return new ListCommentDto(commentDao.getComments()
                .orElseThrow(() -> new CommentTableIsEmptyException("Comment table is empty")
                ));
    }

    public CommentDto updateComment(UpdateCommentDto dto, Long id) throws CommentNotFoundException {
        commentDao.updateComment(dto.getText(), id);
        return commentDao.getComment(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found by id:" + id)
                );
    }

    public CommentDto deleteComment(Long id) throws CommentNotFoundException {
        CommentDto dto = commentDao.getComment(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found by id:" + id)
                );
        commentDao.deleteComment(id);
        return dto;
    }
}
