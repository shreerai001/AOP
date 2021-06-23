package com.example.aop.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "programming_language")
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
