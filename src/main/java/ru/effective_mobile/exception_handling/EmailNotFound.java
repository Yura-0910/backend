package ru.effective_mobile.exception_handling;

public class EmailNotFound extends RuntimeException{

  public EmailNotFound(String message) {
    super(message);
  }
}
