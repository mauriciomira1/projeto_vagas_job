package com.mauriciomiranda.projeto_vagas_job.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity // Ativar PreAuthorize com roles
public class SecurityConfig {

  @Autowired
  SecurityFilter securityFilter;

  @Autowired
  SecurityCandidateFilter securityCandidateFilter;

  private static final String[] SWAGGER_LIST = {
      "/swagger-ui/**",
      "/v3/api-docs/**",
      "/swagger-resources/**"
  };

  // Para sobrescrever configuração padrão do spring security
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers(SWAGGER_LIST).permitAll();
          auth.requestMatchers("/candidate/").permitAll()
              .requestMatchers("/company/").permitAll()
              .requestMatchers("/company/auth").permitAll()
              .requestMatchers("/candidate/auth").permitAll();
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
    return http.build();
  }

  // Criptografia de senha do usuário
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
