package ru.effective_mobile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
  String fio;
  String email;
  String password;
  String role;
}
