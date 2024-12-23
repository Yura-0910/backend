package ru.effective_mobile.db.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.db.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
 boolean existsByEmail(String email);
}
