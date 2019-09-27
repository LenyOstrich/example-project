package com.exampleproject.engine.services;

import com.exampleproject.database.entities.Organization;

import java.util.List;

public interface OrganizationService {
    public List<Organization> getAll();
    public Organization save(Organization org);
    public void deleteById (Long id);
    public Organization get(Long id);
}
