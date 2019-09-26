package com.exampleproject.database.repositories;

import com.exampleproject.database.entities.Organization;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long>{

}
