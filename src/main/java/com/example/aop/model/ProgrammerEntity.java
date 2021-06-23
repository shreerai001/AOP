package com.example.aop.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "programmer")
public class ProgrammerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "programmingId", referencedColumnName = "id")
    private List<ProgrammingLanguage> programmingLanguage;

}
