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
}
