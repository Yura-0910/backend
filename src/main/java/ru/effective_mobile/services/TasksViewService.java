package ru.effective_mobile.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.CommentsRepository;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.db.repository.UsersRepository;
import ru.effective_mobile.exception_handling.UnknownUserType;
import ru.effective_mobile.exception_handling.UserNotFound;

@Service
@AllArgsConstructor
public class TasksViewService {

  private UsersRepository usersRepository;
  private TasksRepository tasksRepository;
  private CommentsRepository commentsRepository;

  /**
   * Получаем все задачи конкретного автора или исполнителя вместе со всеми комментариями
   * к задачам
   */
  public List<Tasks> allTasks(long userId, String userType, Pageable pageable) {
    //Проверим, есть ли в БД пользователь (автор или исполнитель) с заданным ID.
    userIsExist(userId);

    //Получаем все задачи со всеми комментариями к этим задачам
    return getAllUserTasks(userId, userType, pageable);
  }

  //Проверим, есть ли в БД пользователь (автор или исполнитель) с заданным ID.
  public void userIsExist(long userId) {
    Users user = usersRepository.findById(userId).orElse(null);
    if (user == null) {
      throw new UserNotFound(
          "Пользователь (автор или исполнитель) с ID = " + userId + " не найден");
    }
  }

  //Получаем все задачи конкретного автора или исполнителя
  public List<Tasks> getAllUserTasks(long userId, String userType, Pageable pageable) {
    List<Tasks> allTasks;
    if (userType.equals("author")) {
      allTasks = tasksRepository.findAllByAuthorId(userId, pageable);
    } else if (userType.equals("executor")) {
      allTasks = tasksRepository.findAllByExecutorId(userId, pageable);
    } else {
      throw new UnknownUserType("Параметр запроса = userType должен быть:: author или executor");
    }
    return allTasks;
  }
}
