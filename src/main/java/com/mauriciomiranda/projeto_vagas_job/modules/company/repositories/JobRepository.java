package com.mauriciomiranda.projeto_vagas_job.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

  // Busca de vagas por palavras-chave

  // Select * from job where description like %filter%
  List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
