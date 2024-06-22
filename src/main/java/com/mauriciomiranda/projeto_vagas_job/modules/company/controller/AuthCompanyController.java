package com.mauriciomiranda.projeto_vagas_job.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.modules.company.dto.AuthCompanyDTO;
import com.mauriciomiranda.projeto_vagas_job.modules.company.useCases.AuthCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

  @Autowired
  AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  @Tag(name = "Empresa", description = "Ações relacionadas à empresa")
  @Operation(summary = "Fazer login como empresa", description = "Login da empresa")
  public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
    try {
      var token = this.authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

    }
  }

}
