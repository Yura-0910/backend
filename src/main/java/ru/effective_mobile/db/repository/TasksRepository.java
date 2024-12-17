package ru.effective_mobile.db.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.db.entity.Tasks;

@Repository
public interface TasksRepository extends PagingAndSortingRepository<Tasks, Long> {
   public List<Tasks> findAllByAuthorId(long authorId, Pageable pageable);
   public List<Tasks> findAllByExecutorId(long executorId, Pageable pageable);
   public Optional<Tasks> findByTaskId(long taskId);
   public  Tasks save(Tasks task);
   public void deleteTasksByTaskId(long taskId);
}
