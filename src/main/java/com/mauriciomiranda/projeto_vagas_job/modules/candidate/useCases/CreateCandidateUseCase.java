package com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mauriciomiranda.projeto_vagas_job.exceptions.UserFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(
        candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException("Usuário já cadastrado.");
        });

    var encryptedPassword = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(encryptedPassword);

    return this.candidateRepository.save(candidateEntity);
  }

}
