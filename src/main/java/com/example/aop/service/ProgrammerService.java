package com.example.aop.service;

import com.example.aop.model.ProgrammerEntity;
import com.example.aop.model.ProgrammingLanguage;
import com.example.aop.model.requests.ProgrammerRequest;
import com.example.aop.repository.ProgrammerRepository;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableAspectJAutoProxy
public class ProgrammerService {
    private final ProgrammerRepository programmerRepository;
    private final ProgrammingLanguageService programmingLanguageService;

    public ProgrammerService(ProgrammerRepository programmerRepository, ProgrammingLanguageService programmingLanguageService) {
        this.programmerRepository = programmerRepository;
        this.programmingLanguageService = programmingLanguageService;
    }

    public List<ProgrammerEntity> getAllProgrammers() {
        programmingLanguageService.check();
        return programmerRepository.findAll();
    }


    public String saveProgrammer(final ProgrammerRequest programmerRequest) {
        return programmerRepository.save(ProgrammerEntity.builder()
                .fullName(programmerRequest.getFullName())
                .programmingLanguage(programmerRequest.getProgrammingLanguage().stream()
                        .map(programmingLanguage -> ProgrammingLanguage.builder()
                                .name(programmingLanguage.getProgrammingLanguageName())
                                .build()).collect(Collectors.toList()))
                .build()).getFullName() + " saved";
    }
}
