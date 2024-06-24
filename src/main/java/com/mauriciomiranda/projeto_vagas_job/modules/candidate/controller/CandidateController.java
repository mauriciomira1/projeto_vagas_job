package com.mauriciomiranda.projeto_vagas_job.modules.candidate.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases.CreateCandidateUseCase;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases.ProfileCandidateUseCase;
import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.JobEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Ações relacionadas ao candidato")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @PostMapping("/")
  @Operation(summary = "Cadastro de novo candidato", description = "Cadastro de candidato")
  // @Valid usado para validar campos (conforme parâmetros da entidade)
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Perfil do candidato", description = "Perfil do candidato")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> get(HttpServletRequest request) {

    var idCandidate = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }

  // Busca de jobs por filtro
  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
  // Configurações do Swagger
  @Operation(summary = "Listagem de vagas disponíveis para o candidato", description = "Essa função é responsável por listar todas as vagas disponíveis, baseada no filtro")
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }
}
