package ru.effective_mobile.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.RolesRepository;
import ru.effective_mobile.db.repository.UsersRepository;
import ru.effective_mobile.exception_handling.EmailNotFound;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private UsersRepository usersRepository;
  private RolesRepository rolesRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users user = usersRepository.findByEmail(email);
    if (user == null) {
      throw new EmailNotFound("JWT аутентификация:: не найден user с email = " +
          email);
    }
    return new UserDetailsImpl(user, rolesRepository);
  }
}
