package com.mauriciomiranda.projeto_vagas_job.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.CompanyEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.company.useCases.CreateCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  // Cadastro de empresa
  @PostMapping("/")
  @Tag(name = "Empresa", description = "Ações relacionadas à empresa")
  @Operation(summary = "Cadastro de nova empresa", description = "Cadastro de empresa")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {

    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok().body(result);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
