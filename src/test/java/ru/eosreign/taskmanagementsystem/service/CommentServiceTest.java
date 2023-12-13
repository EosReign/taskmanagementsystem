package ru.eosreign.taskmanagementsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eosreign.taskmanagementsystem.dao.CommentDao;
import ru.eosreign.taskmanagementsystem.dto.NewCommentDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCommentDto;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    public CommentDao commentDao;

    @InjectMocks
    public CommentService commentService;

    @Test
    public void createComment_(NewCommentDto dto) {

    }

    @Test
    public void getComment_(Long id) {

    }

    @Test
    public void getComments_() {

    }

    @Test
    public void updateComment_(UpdateCommentDto dto, Long id) {

    }

    @Test
    public void deleteComment_(Long id) {

    }
}
