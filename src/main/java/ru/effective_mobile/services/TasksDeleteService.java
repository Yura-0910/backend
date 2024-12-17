package ru.effective_mobile.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.dto.DeleteTaskDto;

@Service
@AllArgsConstructor
public class TasksDeleteService {

  private TasksRepository tasksRepository;

  /**
   * Автор задачи удаляет свою задачу
   */
  @Transactional
  public ResponseEntity<String> deleteTask(DeleteTaskDto deleteTaskDto) {
    Tasks task = tasksRepository.findByTaskId(deleteTaskDto.getTaskId()).orElse(null);
    if (task == null) {
      return new ResponseEntity<>("Задача не найдена:: нечего удалять",
          HttpStatus.NOT_FOUND);
    }

    //Проверяем, что удалять задачу может только автор задачи
    if (deleteTaskDto.getSenderId() != task.getAuthorId()) {
      return new ResponseEntity<>("Удалять задачу может только автор задачи",
          HttpStatus.FORBIDDEN);
    }

    tasksRepository.deleteTasksByTaskId(deleteTaskDto.getTaskId());

    return new ResponseEntity<>("Успешно удалена задача c id = " + task.getTaskId(),
        HttpStatus.OK);
  }
}
