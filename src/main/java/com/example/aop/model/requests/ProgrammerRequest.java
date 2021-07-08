package com.example.aop.model.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class ProgrammerRequest implements Serializable {
    private static final long serialVersionUID = -5543936718353790957L;
    private String fullName;
    private List<ProgrammingLanguage> programmingLanguage;
}
