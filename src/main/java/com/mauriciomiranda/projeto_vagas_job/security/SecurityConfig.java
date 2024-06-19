package com.mauriciomiranda.projeto_vagas_job.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  SecurityFilter securityFilter;

  @Autowired
  SecurityCandidateFilter securityCandidateFilter;

  // Para sobrescrever configuração padrão do spring security
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/candidate/").permitAll()
              .requestMatchers("/company/").permitAll()
              .requestMatchers("/auth/company").permitAll()
              .requestMatchers("/auth/candidate").permitAll();
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class);
    return http.build();
  }

  // Criptografia de senha do usuário
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
