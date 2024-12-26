package ru.effective_mobile.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.effective_mobile.db.entity.Roles;
import ru.effective_mobile.db.entity.Users;
import ru.effective_mobile.db.repository.RolesRepository;
import ru.effective_mobile.db.repository.UsersRepository;
import ru.effective_mobile.dto.SignUpDto;
import ru.effective_mobile.jwt.JwtService;

@Service
@AllArgsConstructor
public class SignUpService {

  private UsersRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private RolesRepository rolesRepository;
  private JwtService jwtService;

  /**
   * Регистрация нового пользователя
   */
  public ResponseEntity<String> signUp(SignUpDto signUpDTO) {
    if (userRepository.existsByEmail(signUpDTO.getEmail())) {
      return new ResponseEntity<>("Уже есть пользователь с таким Email = " +
          signUpDTO.getEmail(), HttpStatus.IM_USED);
    }

    Users user = new Users();
    user.setFio(signUpDTO.getFio());
    user.setEmail(signUpDTO.getEmail());
    user.setPassword(passwordEncoder.encode(signUpDTO.getPassword())); //Шифруем пароль

    Roles role = rolesRepository.findByRole(signUpDTO.getRole()).orElse(null);
    if(role == null){
      return new ResponseEntity<>("Такая роль (" + signUpDTO.getRole() + "):: не существует",
          HttpStatus.NOT_FOUND);
    }
    else {
      user.setRoleId(role.getRoleId());
    }
    userRepository.save(user);

    String token = jwtService.generateToken(user);
    return new ResponseEntity<>("Вы успешно зарегистрировались, ваш токен:: " + token,
        HttpStatus.CREATED);
  }
}
