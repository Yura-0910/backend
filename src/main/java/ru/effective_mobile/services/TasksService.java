package ru.effective_mobile.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.dto.EditTaskDto;
import ru.effective_mobile.dto.TaskDto;

@Service
@AllArgsConstructor
public class TasksService {

  private final TasksRepository tasksRepository;

  /**
   * Создание новой задачи
   */
  public ResponseEntity<String> addTask(TaskDto taskDTO) {
    Tasks task = new Tasks();
    if (taskDTO.getTaskId() != 0) {
      task.setTaskId(taskDTO.getTaskId());
    }
    task.setHeader(taskDTO.getHeader());
    task.setDescription(taskDTO.getDescription());
    task.setStatusId(taskDTO.getStatusId());
    task.setPriorityId(taskDTO.getPriorityId());
    task.setAuthorId(taskDTO.getAuthorId());
    task.setExecutorId(taskDTO.getExecutorId());

    task = tasksRepository.save(task);

    return new ResponseEntity<>("Задача успешно создана, id = " + task.getTaskId(),
        HttpStatus.CREATED);
  }

  /**
   * Пользователь редактирует свою задачу
   */
  public ResponseEntity<String> editTask(EditTaskDto editTaskDto) {
    Tasks task = tasksRepository.findById(editTaskDto.getTaskId()).orElse(null);
    if (task == null) {
      return new ResponseEntity<>("Задача не найдена:: нечего редактировать",
          HttpStatus.NOT_FOUND);
    }

    //Проверяем, что редактировать будем только свою задачу
    if (editTaskDto.getSenderId() != task.getAuthorId()) {
      return new ResponseEntity<>("Вы пытаетесь отредактировать не свою задачу",
          HttpStatus.FORBIDDEN);
    }

    //Обновляем поля задачи на основе данных из DTO
    task = setValuesForFieldsOfTask(task, editTaskDto);

    //Сохраняем изменения в БД
    task = tasksRepository.save(task);

    return new ResponseEntity<>("Задача c id = " + task.getTaskId() + ":: успешно "
        + "отредактирована", HttpStatus.CREATED);
  }

  //Устанавливаем для Task значения полей
  public Tasks setValuesForFieldsOfTask(Tasks task, TaskDto taskDTO) {
    task.setHeader(taskDTO.getHeader());
    task.setDescription(taskDTO.getDescription());
    task.setStatusId(taskDTO.getStatusId());
    task.setPriorityId(taskDTO.getPriorityId());
    task.setAuthorId(taskDTO.getAuthorId());
    task.setExecutorId(taskDTO.getExecutorId());
    return task;
  }
}
