package ru.effective_mobile.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.dto.TaskDto;
import ru.effective_mobile.services.TasksService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TasksController {
  private TasksService tasksService;

  /**
   * Создание задачи
   */
  @PostMapping("/add")
  public ResponseEntity<String> addTask(@RequestBody TaskDto taskDTO) {
    return tasksService.addTask(taskDTO);
  }
}
