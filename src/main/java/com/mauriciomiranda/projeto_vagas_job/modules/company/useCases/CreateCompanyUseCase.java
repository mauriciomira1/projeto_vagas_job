package com.mauriciomiranda.projeto_vagas_job.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauriciomiranda.projeto_vagas_job.exceptions.UserFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.CompanyEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent(user -> {
          throw new UserFoundException("Empresa já cadastrada.");
        });

    return this.companyRepository.save(companyEntity);

  }
}
