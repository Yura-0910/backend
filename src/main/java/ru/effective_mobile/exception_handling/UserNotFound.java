package ru.effective_mobile.exception_handling;

public class UserNotFound extends RuntimeException{

  public UserNotFound(String message) {
    super(message);
  }
}
