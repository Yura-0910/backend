package ru.effective_mobile.db.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective_mobile.db.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

  //Ищем роль по имени
  Optional<Roles> findByRole(String role);

  //Находим роль по id
  Roles findByRoleId(long id);
}
