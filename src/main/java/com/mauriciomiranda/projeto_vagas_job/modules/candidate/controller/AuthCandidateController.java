package com.mauriciomiranda.projeto_vagas_job.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.modules.candidate.dto.AuthCandidateRequestDTO;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases.AuthCandidateUseCase;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases.ProfileCandidateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @Autowired
  ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping("/auth")
  @Tag(name = "Candidato")
  @Operation(summary = "Fazer login como candidato", description = "Login do candidato")

  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.ok().body(token);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }

}
