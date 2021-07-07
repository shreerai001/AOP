package com.example.aop.repository;

import com.example.aop.model.ActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLoggerRepository extends JpaRepository<ActivityLogEntity, Long> {
}
