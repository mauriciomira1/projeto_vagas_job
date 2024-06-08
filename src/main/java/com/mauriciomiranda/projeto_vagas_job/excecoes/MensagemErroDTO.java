package com.mauriciomiranda.projeto_vagas_job.excecoes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemErroDTO {
  private String mensagem;
  private String campo;
}
