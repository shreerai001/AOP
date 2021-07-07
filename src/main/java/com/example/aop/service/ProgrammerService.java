package com.example.aop.service;

import com.example.aop.model.ProgrammerEntity;
import com.example.aop.model.ProgrammingLanguage;
import com.example.aop.model.requests.ProgrammerRequest;
import com.example.aop.repository.ProgrammerRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgrammerService {
    private final ProgrammerRepository programmerRepository;

    public ProgrammerService(ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public List<ProgrammerEntity> getAllProgrammers() {
        return programmerRepository.findAll();
    }

    public String saveProgrammer(final ProgrammerRequest programmerRequest) {
        return programmerRepository.save(ProgrammerEntity.builder()
                .fullName(programmerRequest.getFullName())
                .programmingLanguage(programmerRequest.getProgrammingLanguage().stream()
                        .map(programmingLanguage -> ProgrammingLanguage.builder()
                                .name(programmingLanguage.getProgrammingLanguage())
                                .build()).collect(Collectors.toList()))
                .build()).getFullName() + " saved";
    }
}
