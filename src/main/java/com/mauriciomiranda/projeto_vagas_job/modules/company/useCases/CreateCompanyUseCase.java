package com.mauriciomiranda.projeto_vagas_job.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mauriciomiranda.projeto_vagas_job.exceptions.UserFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.CompanyEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CompanyEntity execute(CompanyEntity companyEntity) {

    this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent(user -> {
          throw new UserFoundException("Empresa já cadastrada.");
        });

    var encryptedPassword = passwordEncoder.encode(companyEntity.getPassword());

    companyEntity.setPassword(encryptedPassword);

    return this.companyRepository.save(companyEntity);

  }
}
