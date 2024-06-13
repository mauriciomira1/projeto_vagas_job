package com.mauriciomiranda.projeto_vagas_job.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.exceptions.UserFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CandidateRepository candidateRepository;

  @PostMapping("/")
  // @Valid usado para validar campos (conforme parâmetros da entidade)
  public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
    CandidateEntity candidateExists = this.candidateRepository.findByUsernameOrEmail(candidateEntity.getEmail(),
        candidateEntity.getUsername());
    if (candidateExists != null) {
      throw new UserFoundException("Usuário já cadastrado.");
    }
    return this.candidateRepository.save(candidateEntity);
  }
}
