package com.mauriciomiranda.projeto_vagas_job.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.JobEntity;
import com.mauriciomiranda.projeto_vagas_job.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  public JobEntity create(JobEntity jobEntity) {
    return this.jobRepository.save(jobEntity);
  }

}
