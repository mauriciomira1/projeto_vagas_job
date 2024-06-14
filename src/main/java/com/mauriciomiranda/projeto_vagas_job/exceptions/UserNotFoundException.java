package com.mauriciomiranda.projeto_vagas_job.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
