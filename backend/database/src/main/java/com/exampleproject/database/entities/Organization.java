package com.exampleproject.database.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_Sequence")
    @SequenceGenerator(name = "organization_Sequence", sequenceName = "ORGANIZATION_SEQ")
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "organization")
    @JsonManagedReference(value = "departments")
    private List<Department> departments;
}
