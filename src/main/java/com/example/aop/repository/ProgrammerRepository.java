package com.example.aop.repository;

import com.example.aop.model.ProgrammerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepository extends JpaRepository<ProgrammerEntity, Long> {
}
