package ru.effective_mobile.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.dto.EditStatusDto;

@Service
@AllArgsConstructor
public class StatusEditService {
  private final TasksRepository tasksRepository;

  /**
   * Владелец задачи меняет статус задачи
   */
  public ResponseEntity<String> statusEdit(EditStatusDto editStatusDto) {
    Tasks task = tasksRepository.findById(editStatusDto.getTaskId()).orElse(null);
    if (task == null) {
      return new ResponseEntity<>("Задача не найдена:: нечего менять",
          HttpStatus.NOT_FOUND);
    }

    //Проверяем, что менять статус будем только у своей задачи
    if (editStatusDto.getSenderId() != task.getAuthorId()) {
      return new ResponseEntity<>("Вы пытаетесь поменять статус не у своей задачи",
          HttpStatus.FORBIDDEN);
    }

    //Меняем статус своей задачи
    task.setStatusId(editStatusDto.getStatusId());

    //Сохраняем изменения в БД
    task = tasksRepository.save(task);

    return new ResponseEntity<>("У задачи c id = " + task.getTaskId() + ":: успешно "
        + "изменен статус", HttpStatus.ACCEPTED);
  }
}
