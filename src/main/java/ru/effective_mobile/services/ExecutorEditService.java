package ru.effective_mobile.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.dto.EditExecutorDto;

@Service
@AllArgsConstructor
public class ExecutorEditService {
  private final TasksRepository tasksRepository;

  /**
   * Владелец задачи может менять исполнителя задачи
   */
  public ResponseEntity<String> editExecutor(EditExecutorDto editExecutorDto) {
    Tasks task = tasksRepository.findById(editExecutorDto.getTaskId()).orElse(null);
    if (task == null) {
      return new ResponseEntity<>("Задача не найдена:: нечего менять",
          HttpStatus.NOT_FOUND);
    }

    //Проверяем, что менять исполнителя задачи может только владелец задачи
    if (editExecutorDto.getSenderId() != task.getAuthorId()) {
      return new ResponseEntity<>("Менять исполнителя задачи может только владелец задачи",
          HttpStatus.FORBIDDEN);
    }

    //Меняем исполнителя задачи
    task.setExecutorId(editExecutorDto.getExecutorId());

    //Сохраняем изменения в БД
    task = tasksRepository.save(task);

    return new ResponseEntity<>("У задачи c id = " + task.getTaskId() + ":: успешно "
        + "изменен исполнитель", HttpStatus.ACCEPTED);
  }
}
