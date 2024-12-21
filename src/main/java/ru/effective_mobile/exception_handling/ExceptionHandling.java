package ru.effective_mobile.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
  //Обрабатываем следующие типы исключений
  @ExceptionHandler({UserNotFound.class, UnknownUserType.class})
  public ResponseEntity<String> handlingErrorsViewingTasks(){
    return new ResponseEntity<>("Неправильные параметры запроса", HttpStatus.BAD_REQUEST);
  }
}
