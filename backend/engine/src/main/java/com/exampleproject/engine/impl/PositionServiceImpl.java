package com.exampleproject.engine.impl;

import com.exampleproject.database.entities.Position;
import com.exampleproject.database.repositories.PositionRepository;
import com.exampleproject.engine.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {


    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAll() {
        return (List<Position>) positionRepository.findAll();
    }

    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Position get(Long id) {
        return positionRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
