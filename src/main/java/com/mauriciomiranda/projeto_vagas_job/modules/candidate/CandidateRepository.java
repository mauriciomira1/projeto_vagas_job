package com.mauriciomiranda.projeto_vagas_job.modules.candidate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// O @Repository é dispensado, pois ao usar 'interface' e 'JpaRepository' ele já entende que é um Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

  CandidateEntity findByUsernameOrEmail(String email, String username);

}