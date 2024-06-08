package com.mauriciomiranda.projeto_vagas_job.excecoes;

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
public class lidarComExcecoes {

  private MessageSource messageSource;

  public lidarComExcecoes(MessageSource mensagem) {
    this.messageSource = mensagem;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<MensagemErroDTO>> ExcecaoLidarComArgumentoInvalido(MethodArgumentNotValidException e) {
    List<MensagemErroDTO> dto = new ArrayList<>();

    e.getBindingResult().getFieldErrors().forEach(err -> {
      String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
      MensagemErroDTO erro = new MensagemErroDTO(mensagem, err.getField());
      dto.add(erro);
    });

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }
}
