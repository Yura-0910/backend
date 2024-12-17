package ru.effective_mobile.controllers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.dto.EditExecutorDto;
import ru.effective_mobile.dto.EditStatusDto;
import ru.effective_mobile.dto.EditTaskDto;
import ru.effective_mobile.dto.TaskDto;
import ru.effective_mobile.services.ExecutorEditService;
import ru.effective_mobile.services.StatusEditService;
import ru.effective_mobile.services.TasksService;
import ru.effective_mobile.services.TasksViewService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TasksController {

  private TasksService tasksService;
  private StatusEditService statusEditService;
  private ExecutorEditService executorEditService;
  private TasksViewService tasksViewService;

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

  /**
   * Владелец задачи может менять исполнителя задачи
   */
  @PostMapping("/editExecutor")
  public ResponseEntity<String> editExecutor(@RequestBody EditExecutorDto editExecutorDto) {
    return executorEditService.editExecutor(editExecutorDto);
  }

  /**
   * Просмотр всех задач (с комментариями) конкретно автора или конкретного исполнителя
   *
   * @param userId    id автора или id исполнителя
   * @param userType  тип пользователя:: автор\author или исполнитель\executor
   * @param page      номер страницы (с нуля): используется для пегинации
   * @param size      количество элементов на странице: используется для пегинации
   * @param direction направление сортировки
   * @return список со всеми задачами конкретного автора или исполнителя
   */
  @GetMapping("/allTasks/{userId}")
  public List<Tasks> allTasks(@PathVariable long userId,
      @RequestParam(defaultValue = "author") String userType,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "asc") String direction) {
    Pageable pageable = null;
    if (direction.contains("asc")) {
      pageable = PageRequest.of(page, size, Sort.by("taskId"));
    } else {
      pageable = PageRequest.of(page, size, Sort.by("taskId").descending());
    }
    return tasksViewService.allTasks(userId, userType, pageable);
  }
}