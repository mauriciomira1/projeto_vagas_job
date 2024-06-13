package com.mauriciomiranda.projeto_vagas_job.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
  private String mensagem;
  private String campo;
}
