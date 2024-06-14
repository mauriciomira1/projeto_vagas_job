package com.mauriciomiranda.projeto_vagas_job.modules.company.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.modules.company.dto.AuthCompanyDTO;
import com.mauriciomiranda.projeto_vagas_job.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/company")
  public String create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    return this.authCompanyUseCase.execute(authCompanyDTO);
  }

}
