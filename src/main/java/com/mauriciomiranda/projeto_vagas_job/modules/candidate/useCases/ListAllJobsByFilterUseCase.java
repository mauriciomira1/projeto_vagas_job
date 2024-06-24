package com.mauriciomiranda.projeto_vagas_job.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.JobEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {

    return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);

  }
}
