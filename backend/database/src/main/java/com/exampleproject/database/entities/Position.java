package com.exampleproject.database.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_sequence")
    @SequenceGenerator(name = "position_sequence", sequenceName = "POSITION_SEQ")
    private Long id;

    @Column
    private String name;

    @Column
    private int salary;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "position")
    @JsonManagedReference
    private List<Employee> employees;
}
