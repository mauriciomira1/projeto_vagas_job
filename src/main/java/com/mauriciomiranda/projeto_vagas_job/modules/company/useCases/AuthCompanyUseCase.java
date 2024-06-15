package com.mauriciomiranda.projeto_vagas_job.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mauriciomiranda.projeto_vagas_job.exceptions.UserNotFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.company.dto.AuthCompanyDTO;
import com.mauriciomiranda.projeto_vagas_job.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

    var user = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
      throw new UserNotFoundException("Nome de usuário/senha incorretos");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), user.getPassword());

    // Senhas não são iguais (retorna erro)
    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    // Senhas são iguais (gera o token)
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("Projeto Vagas Job")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .withSubject(user.getId().toString())
        .sign(algorithm);
    return token;
  }

}
