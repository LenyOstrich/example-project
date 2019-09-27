package com.exampleproject.engine.services;

import com.exampleproject.database.entities.Position;

import java.util.List;

public interface PositionService {
    public List<Position> getAll();
    public Position save(Position org);
    public void deleteById (Long id);
    public Position get(Long id);
}
