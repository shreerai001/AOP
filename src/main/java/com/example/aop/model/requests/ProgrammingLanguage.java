package com.example.aop.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum ProgrammingLanguage implements Serializable {

    JAVA("java"), PYTHON("python"), SCALA("scala"), RUST("rust"), GOLANG("golang"),
    ASSEMBLY("assembly"), C("c"), CPLUS("c++"), CLOSURE("closure"), HASKELL("haskell");

    private String programmingLanguage;


}
