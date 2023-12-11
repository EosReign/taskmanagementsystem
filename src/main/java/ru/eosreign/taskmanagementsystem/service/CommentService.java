package ru.eosreign.taskmanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.eosreign.taskmanagementsystem.dao.CommentDao;
import ru.eosreign.taskmanagementsystem.dto.*;

import java.util.ArrayList;

@Service
public class CommentService {
    private final CommentDao commentDao;
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    public CommentDto createComment(NewCommentDto dto) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentDao.createComment(dto));
        return commentDto;
    }

    public CommentDto getComment(Long id) {
        return commentDao.getComment(id);
    }

    public ListCommentDto getComments() {
        return new ListCommentDto(commentDao.getComments());
    }

    public CommentDto updateComment(UpdateCommentDto dto, Long id) {
        commentDao.updateComment(dto.getText(), id);
        return commentDao.getComment(id);
    }

    public void deleteComment(Long id) {
        commentDao.deleteComment(id);
    }
}
