package ru.effective_mobile.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
  //Обрабатываем следующие типы исключений
  @ExceptionHandler({UserNotFound.class, UnknownUserType.class})
  public ResponseEntity<String> handlingErrorsViewingTasks(){
    return new ResponseEntity<>("Неправильные параметры запроса", HttpStatus.BAD_REQUEST);
  }

  /**
   * Если не срабатывает валидация, то выбрасывается исключение "MethodArgumentNotValidException",
   * которое обрабатываем отдельно
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> validateDtoField(MethodArgumentNotValidException ex){
    String defaultMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
    return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
  }
}
