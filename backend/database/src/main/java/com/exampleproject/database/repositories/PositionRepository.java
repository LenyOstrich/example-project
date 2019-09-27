package com.exampleproject.database.repositories;

import com.exampleproject.database.entities.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PositionRepository extends CrudRepository<Position, Long>{

}
