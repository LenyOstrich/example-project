package com.exampleproject.engine.impl;

import com.exampleproject.database.entities.Organization;
import com.exampleproject.database.repositories.OrganizationRepository;
import com.exampleproject.engine.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAll() {
        return (List<Organization>) organizationRepository.findAll();
    }

    @Override
    public Organization save(Organization org) {
        return organizationRepository.save(org);
    }

    @Override
    public void deleteById(Long id) {
        organizationRepository.deleteById(id);
    }

    @Override
    public Organization get(Long id) {
        return organizationRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
