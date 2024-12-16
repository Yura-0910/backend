package ru.effective_mobile.exception_handling;

public class UnknownUserType extends RuntimeException{

  public UnknownUserType(String message) {
    super(message);
  }
}
