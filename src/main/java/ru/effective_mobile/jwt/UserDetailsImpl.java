package ru.effective_mobile.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.effective_mobile.db.entity.Roles;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.RolesRepository;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private Users user;
  private RolesRepository rolesRepository;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    //Находим роль по id
    Optional<Roles> rolesOptional = rolesRepository.findById(user.getRoleId());
    if (rolesOptional.isPresent()) {
      //Из Optional вытаскиваем объект типа Roles
      Roles role = rolesOptional.get();
      //Из Roles вытаскиваем имя роли
      String roleName = role.getRole();
      authorities.add(new SimpleGrantedAuthority(roleName));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user != null ? user.getEmail() : "UserNotFoundInJWT";
  }

  //Из интерфейса UserDetails наследуются еще методы с дефолтной реализацией
}
