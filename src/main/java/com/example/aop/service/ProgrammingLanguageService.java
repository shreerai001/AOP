package com.example.aop.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProgrammingLanguageService {

    public List<String> check() {
        return Arrays.asList("Hello", "Hi", "Namaste");
    }
}
