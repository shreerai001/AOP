package com.example.aop.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
