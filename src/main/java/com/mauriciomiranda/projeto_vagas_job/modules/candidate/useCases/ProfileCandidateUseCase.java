package com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauriciomiranda.projeto_vagas_job.exceptions.UserNotFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateRepository;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {

    var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundException("Usuário não encontrado");
        });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .name(candidate.getName())
        .email(candidate.getEmail())
        .id(idCandidate)
        .username(candidate.getUsername())
        .build();

    return candidateDTO;

  }
}
