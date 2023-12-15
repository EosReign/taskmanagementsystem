package ru.eosreign.taskmanagementsystem.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.eosreign.taskmanagementsystem.dao.CommentDao;
import ru.eosreign.taskmanagementsystem.dto.CommentDto;
import ru.eosreign.taskmanagementsystem.dto.ListCommentDto;
import ru.eosreign.taskmanagementsystem.dto.NewCommentDto;
import ru.eosreign.taskmanagementsystem.dto.UpdateCommentDto;
import ru.eosreign.taskmanagementsystem.exception.CommentNotFoundException;
import ru.eosreign.taskmanagementsystem.exception.CommentTableIsEmptyException;
import ru.eosreign.taskmanagementsystem.exception.CreatingCommentFailedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    public CommentDao commentDao;

    @InjectMocks
    public CommentService commentService;

    private CommentDto testCommentDto;

    private NewCommentDto testNewCommentDto;

    private UpdateCommentDto testUpdateCommentDto;

    @BeforeEach
    public void init() {
        testCommentDto = methodReturnTestCommentDto();
        testNewCommentDto = methodReturnTestNewCommentDto();
        testUpdateCommentDto = methodReturnTestUpdateCommentDto();
    }

    @AfterEach
    public void teardown() {
        testCommentDto = null;
        testNewCommentDto = null;
        testUpdateCommentDto = null;
    }

    @Test
    public void createComment_ReturnCommentDto() {
        Mockito.when(commentDao.createComment(testNewCommentDto))
                .thenReturn(Optional.of(1L));
        Mockito.when(commentDao.getComment(1L))
                .thenReturn(Optional.of(testCommentDto));

        assertThat(testCommentDto).isEqualTo(commentService.createComment(testNewCommentDto));
    }

    @Test
    public void createComment_CreatingFailed_ThrownException() {
        Mockito.when(commentDao.createComment(testNewCommentDto))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.createComment(testNewCommentDto))
                .isInstanceOf(CreatingCommentFailedException.class)
                .hasMessageContaining("Creating comment was failed");
    }

    @Test
    public void createComment_CommentNotFound_ThrownException() {
        Mockito.when(commentDao.createComment(testNewCommentDto))
                .thenReturn(Optional.of(1L));
        Mockito.when(commentDao.getComment(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.createComment(testNewCommentDto))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessageContaining("Comment not found by id:" + 1L);
    }

    @Test
    public void getComment_ReturnCommentDto() {
        Mockito.when(commentDao.getComment(1L))
                .thenReturn(Optional.of(testCommentDto));

        assertThat(testCommentDto).isEqualTo(commentService.getComment(1L));
    }

    @Test
    public void getComment_ThrownException() {
        Mockito.when(commentDao.getComment(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.getComment(1L))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessageContaining("Comment not found by id:" + 1L);
    }

    @Test
    public void getComments_ReturnListCommentDto() {
        List<CommentDto> list = new ArrayList<>();
        list.add(testCommentDto);
        ListCommentDto response = new ListCommentDto(list);

        Mockito.when(commentDao.getComments())
                .thenReturn(Optional.of(list));

        assertThat(response).isEqualTo(commentService.getComments());
    }

    @Test
    public void getComments_ThrownException() {
        Mockito.when(commentDao.getComments())
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.getComments())
                .isInstanceOf(CommentTableIsEmptyException.class)
                .hasMessageContaining("Comment table is empty");
    }

    @Test
    public void updateComment_ReturnCommentDto() {
        Mockito.when(commentDao.getComment( 1L))
                .thenReturn(Optional.of(testCommentDto));

        assertThat(testCommentDto).isEqualTo(commentService.updateComment(testUpdateCommentDto, 1L));
    }

    @Test
    public void updateComment_ThrownException() {
        Mockito.when(commentDao.getComment( 1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.getComment(1L))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessageContaining("Comment not found by id:" + 1L);
    }

    @Test
    public void deleteComment_ReturnCommentDto() {
        Mockito.when(commentDao.getComment(1L))
                .thenReturn(Optional.of(testCommentDto));

        assertThat(testCommentDto).isEqualTo(commentService.deleteComment(1L));
    }

    @Test
    public void deleteComment_ThrownException() {
        Mockito.when(commentDao.getComment(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> commentService.getComment(1L))
                .isInstanceOf(CommentNotFoundException.class)
                .hasMessageContaining("Comment not found by id:" + 1L);
    }

    private static CommentDto methodReturnTestCommentDto() {
        CommentDto dto = new CommentDto();
        dto.setId(1L);
        dto.setText("bla bla bla");
        dto.setAuthorId(1L);
        dto.setTaskId(1L);
        dto.setCreatedAt(LocalDate.now());
        return dto;
    }

    private static UpdateCommentDto methodReturnTestUpdateCommentDto() {
        return new UpdateCommentDto("bla bla bla");
    }

    private static NewCommentDto methodReturnTestNewCommentDto() {
        NewCommentDto dto = new NewCommentDto();
        dto.setText("bla bla bla");
        dto.setAuthorId(1L);
        dto.setTaskId(1L);
        return dto;
    }
}
