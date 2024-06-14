package com.mauriciomiranda.projeto_vagas_job.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Length(min = 5, max = 50, message = "O campo [username] deve ter pelo menos 5 (cinco) caracteres")
  private String username;

  @Length(min = 3, max = 200, message = "O campo [name] deve ter pelo menos 3 (três) caracteres")
  private String name;

  @Length(min = 5, max = 50, message = "O campo [email] deve ter pelo menos 5 (cinco) caracteres")
  private String email;

  @Length(min = 6, max = 50, message = "O campo [password] deve ter pelo menos 6 (seis) caracteres")
  private String password;

  private String website;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
