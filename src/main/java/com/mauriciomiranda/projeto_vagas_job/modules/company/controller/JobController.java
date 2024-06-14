package com.mauriciomiranda.projeto_vagas_job.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.JobEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.company.useCases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> execute(@Valid @RequestBody JobEntity jobEntity) {
    try {
      var result = this.createJobUseCase.create(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
