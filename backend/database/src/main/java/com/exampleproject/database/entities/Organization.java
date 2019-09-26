package com.exampleproject.database.entities;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "organization_Sequence")
    @SequenceGenerator(name = "organization_Sequence", sequenceName = "ORGANIZATION_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    public Organization() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
