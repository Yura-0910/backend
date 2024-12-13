package ru.effective_mobile.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.dto.TaskDto;

@Service
@AllArgsConstructor
public class TasksService {

  private final TasksRepository tasksRepository;

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
}
