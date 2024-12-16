package ru.effective_mobile.services;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Comments;
import ru.effective_mobile.db.entity.Tasks;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.CommentsRepository;
import ru.effective_mobile.db.repository.TasksRepository;
import ru.effective_mobile.db.repository.UsersRepository;
import ru.effective_mobile.dto.TaskAndCommentsDto;
import ru.effective_mobile.exception_handling.UnknownUserType;
import ru.effective_mobile.exception_handling.UserNotFound;

@Service
@AllArgsConstructor
public class TasksViewService {

  private UsersRepository usersRepository;
  private TasksRepository tasksRepository;
  private CommentsRepository commentsRepository;

  /**
   * Получаем все задач конкретно автора\исполнителя вместе с комментариями к этим задачам
   */
  public List<TaskAndCommentsDto> allTasks(long userId, String userType) {
    //Проверим, есть ли в БД пользователь (автор или исполнитель) с заданным ID.
    userIsExist(userId);

    //Получим все задачи конкретного автора или исполнителя
    List<Tasks> allTasks = getAllUserTasks(userId, userType);

    //Ко всем задачам конкретного автора\исполнителя добавляем комментарии к этим задачам
    return addCommentsToTasks(allTasks);
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
  public List<Tasks> getAllUserTasks(long userId, String userType) {
    List<Tasks> allTasks;
    if (userType.equals("author")) {
      allTasks = tasksRepository.findAllByAuthorId(userId);
    } else if (userType.equals("executor")) {
      allTasks = tasksRepository.findAllByExecutorId(userId);
    } else {
      throw new UnknownUserType("Параметр запроса = userType должен быть:: author или executor");
    }
    return allTasks;
  }

  //Ко всем задачам конкретного автора\исполнителя добавляем комментарии к этим задачам
  public List<TaskAndCommentsDto> addCommentsToTasks(List<Tasks> allTasks) {
    //Все комментарии для конкретной задачи
    List<Comments> allCommentsForSingleTask;

    //Просто List хранящий все responseDto
    List<TaskAndCommentsDto> response = new ArrayList<>();

    for (Tasks task : allTasks) {
      allCommentsForSingleTask = commentsRepository.findAllByTaskId(task.getTaskId());
      //Dto которое мы возвращаем: со всеми задачами пользователя и с комментариями к ним
      TaskAndCommentsDto responseDto = new TaskAndCommentsDto();
      responseDto.setTask(task);
      responseDto.setComments(allCommentsForSingleTask);
      response.add(responseDto);
    }

    return response;
  }

}
