package ru.effective_mobile.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
