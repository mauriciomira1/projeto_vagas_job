package com.mauriciomiranda.projeto_vagas_job.modules.candidato.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  @PostMapping("/")
  // @Valid usado para validar campos (conforme parâmetros da entidade)
  public void create(@Valid @RequestBody CandidatoEntity candidatoEntity) {
    System.out.println(candidatoEntity.getNome());
  }

}
