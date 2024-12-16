package ru.effective_mobile.db.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.db.entity.Comments;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

  //Находим все комментарии к задаче по id задачи
  public List<Comments> findAllByTaskId(Long taskId);
}
