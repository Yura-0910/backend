package ru.effective_mobile.db.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.db.entity.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
   public List<Tasks> findAllByAuthorId(long authorId);
   public List<Tasks> findAllByExecutorId(long executorId);
}
