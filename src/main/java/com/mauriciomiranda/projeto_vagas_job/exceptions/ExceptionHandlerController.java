package com.mauriciomiranda.projeto_vagas_job.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource mensagem) {
    this.messageSource = mensagem;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> ExcecaoLidarComArgumentoInvalido(MethodArgumentNotValidException e) {
    List<ErrorMessageDTO> dto = new ArrayList<>();

    e.getBindingResult().getFieldErrors().forEach(err -> {
      String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
      ErrorMessageDTO erro = new ErrorMessageDTO(mensagem, err.getField());
      dto.add(erro);
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }
}
