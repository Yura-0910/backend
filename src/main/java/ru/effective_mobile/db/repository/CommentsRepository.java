package ru.effective_mobile.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.db.entity.Comments;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
