package ru.effective_mobile.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.db.repository.UsersRepository;
import ru.effective_mobile.exception_handling.UnknownUserType;
import ru.effective_mobile.exception_handling.UserNotFound;

@Service
@AllArgsConstructor
public class TasksViewService {

  private UsersRepository usersRepository;
  private TasksRepository tasksRepository;

  /**
   * Просмотр всех задач конкретно автора или конкретного исполнителя
   */
  public List<Tasks> allTasks(long userId, String userType) {
    Users user = usersRepository.findById(userId).orElse(null);
    if (user == null) {
      throw new UserNotFound(
          "Такой пользователь (автор или исполнитель) с id = " + userId + " не найден");
    }

    List<Tasks> allTasks; //Все задачи конкретного автора или исполнителя

    if (userType.equals("author")) {
      allTasks = tasksRepository.findAllByAuthorId(userId);
    } else if (userType.equals("executor")) {
      allTasks = tasksRepository.findAllByExecutorId(userId);
    } else {
      throw new UnknownUserType("Параметр запроса = userType должен быть:: author или executor");
    }
    return allTasks;
  }
}
