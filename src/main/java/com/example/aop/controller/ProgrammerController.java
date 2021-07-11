package com.example.aop.controller;

import com.example.aop.model.ProgrammerEntity;
import com.example.aop.model.requests.ProgrammerRequest;
import com.example.aop.service.ProgrammerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmer")
public class ProgrammerController {

    private final ProgrammerService programmerService;

    public ProgrammerController(final ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }


    @GetMapping
    public ResponseEntity<List<ProgrammerEntity>> getAllProgrammers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(programmerService.getAllProgrammers());
    }

    @PostMapping
    public ResponseEntity<String> saveProgrammer(@RequestBody final ProgrammerRequest programmerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(programmerService.saveProgrammer(programmerRequest));
    }

    @GetMapping("/test")
    public ResponseEntity<String> getString() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(programmerService.test());
    }
}
