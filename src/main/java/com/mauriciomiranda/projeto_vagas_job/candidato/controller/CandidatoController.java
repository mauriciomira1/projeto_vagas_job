package com.mauriciomiranda.projeto_vagas_job.candidato.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  CandidatoEntity candidatoEntity = new CandidatoEntity;

  @PostMapping("/")
  public void create() {

  }

}
