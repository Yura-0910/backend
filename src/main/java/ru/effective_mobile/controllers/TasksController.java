package ru.effective_mobile.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.dto.EditStatusDto;
import ru.effective_mobile.dto.EditTaskDto;
import ru.effective_mobile.dto.TaskDto;
import ru.effective_mobile.services.StatusEditService;
import ru.effective_mobile.services.TasksService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TasksController {

  private TasksService tasksService;
  private StatusEditService statusEditService;

  /**
   * Создание новой задачи
   */
  @PostMapping("/add")
  public ResponseEntity<String> addTask(@RequestBody TaskDto taskDTO) {
    return tasksService.addTask(taskDTO);
  }

  /**
   * Пользователь редактирует свою задачу
   */
  @PostMapping("/edit")
  public ResponseEntity<String> editTask(@RequestBody EditTaskDto editTaskDto) {
    return tasksService.editTask(editTaskDto);
  }

  /**
   * Владелец задачи или исполнитель задачи:: меняет статус своей задачи
   */
  @PostMapping("/editStatus")
  public ResponseEntity<String> statusEdit(@RequestBody EditStatusDto editStatusDto) {
    return statusEditService.statusEdit(editStatusDto);
  }
}