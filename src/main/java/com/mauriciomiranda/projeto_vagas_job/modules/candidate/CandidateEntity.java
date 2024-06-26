package com.mauriciomiranda.projeto_vagas_job.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // para criar automaticamente getters e setters
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Length(min = 5, max = 70, message = "Digite o seu nome completo")
  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O nome de usuário de não deve conter espaços")
  private String username;

  private String description;

  @Email(message = "O campo e-mail deve conter um e-mail válido")
  private String email;

  @Length(min = 8, max = 100, message = "A senha deve ter pelo menos 8 (oito) caracteres")
  private String password;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
