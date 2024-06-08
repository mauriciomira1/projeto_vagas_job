package com.mauriciomiranda.projeto_vagas_job.modules.candidato.controller;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // para criar automaticamente getters e setters
public class CandidatoEntity {

  private UUID id;

  @Length(min = 5, max = 70, message = "Digite o seu nome completo")
  private String nome;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O nome de usuário de não deve conter espaços")
  private String username;
  private String descricao;

  @Email(message = "O campo e-mail deve conter um e-mail válido")
  private String email;

  @Length(min = 8, max = 100, message = "A senha deve ter pelo menos 8 (oito) caracteres")
  private String senha;
  private String curriculo;

}
