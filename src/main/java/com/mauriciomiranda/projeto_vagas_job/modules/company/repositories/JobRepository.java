package com.mauriciomiranda.projeto_vagas_job.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mauriciomiranda.projeto_vagas_job.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
