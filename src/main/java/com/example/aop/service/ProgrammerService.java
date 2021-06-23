package com.example.aop.service;

import com.example.aop.model.ProgrammerEntity;
import com.example.aop.repository.ProgrammerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammerService {
    private final ProgrammerRepository programmerRepository;

    public ProgrammerService(ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public List<ProgrammerEntity> getAllProgrammers() {
        return programmerRepository.findAll();
    }

    public String saveProgrammer(ProgrammerEntity programmerEntity) {
        return programmerRepository.save(programmerEntity).getFullName() + " saved";
    }
}
