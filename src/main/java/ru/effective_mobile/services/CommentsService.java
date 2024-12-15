package ru.effective_mobile.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Comments;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.repository.CommentsRepository;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.dto.CommentDto;

@Service
@AllArgsConstructor
public class CommentsService {
  private CommentsRepository commentsRepository;
  private TasksRepository tasksRepository;

  /**
   * Добавляем комментарий к любой задаче
   */
  public ResponseEntity<String> addComment(CommentDto commentDto) {
    Tasks task = tasksRepository.findById(commentDto.getTaskId()).orElse(null);
    if (task == null) {
      return new ResponseEntity<>("Задачу не удалось найти. Комментировать нечего.",
          HttpStatus.NOT_FOUND);
    }

    //Заполняем поля у коментария
    Comments comment = new Comments();
    comment.setContent(commentDto.getContent());
    comment.setTaskId(commentDto.getTaskId());
    comment.setUserId(commentDto.getUserId());

    //Сохраняем комментарий в БД
    comment = commentsRepository.save(comment);

    return new ResponseEntity<>("Комментарий c id = " + comment.getCommentId() + ":: успешно "
        + "добавлен к задаче с id = " + comment.getTaskId(), HttpStatus.ACCEPTED);
  }
}
