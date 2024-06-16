package com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mauriciomiranda.projeto_vagas_job.exceptions.UserNotFoundException;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.CandidateRepository;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.dto.AuthCandidateRequestDTO;
import com.mauriciomiranda.projeto_vagas_job.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateDTO) throws AuthenticationException {
    var user = candidateRepository.findByUsername(authCandidateDTO.username()).orElseThrow(() -> {
      throw new UserNotFoundException("Nome de usuário/senha incorretos");
    });

    var passwordMatches = passwordEncoder.matches(authCandidateDTO.password(), user.getPassword());

    // Senhas não são iguais (retorna erro)
    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    // Senhas são iguais (gera o token)
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("Projeto Vagas Job")
        .withExpiresAt(Instant.now().plus(Duration.ofMinutes(20)))
        .withSubject(user.getId().toString())
        .withClaim("roles", Arrays.asList("candidate"))
        .sign(algorithm);

    var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).build();

    return authCandidateResponse;

  }

}
