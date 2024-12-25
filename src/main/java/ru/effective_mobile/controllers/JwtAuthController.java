package ru.effective_mobile.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.effective_mobile.dto.SignUpDto;
import ru.effective_mobile.services.SignUpService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class JwtAuthController {
  private SignUpService signUpService;

  /**
   * Регистрация нового пользователя
   */
  @PostMapping("/signUpJWT")
  public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDTO) {
    return signUpService.signUp(signUpDTO);
  }

  /**
   * Аутентификация\Авторизация
   */
  @PostMapping("/signInJWT")
  public ResponseEntity<String> signIn() {
    //получаем username, который прошел успешную аутентификацию
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String username = authentication.getPrincipal().toString();
    if (username.equals("anonymousUser")) {
      return new ResponseEntity<>("anonymousUser:: Вы не прошли аутентификацию",
          HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(username + ":: Вы успешно прошли аутентификацию",
          HttpStatus.ACCEPTED);
    }
  }
}
