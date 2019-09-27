package com.exampleproject.web.rest.server;

import com.exampleproject.database.entities.Position;
import com.exampleproject.engine.services.PositionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/save")
    public Position save(@RequestBody @NonNull Position position) {
        return positionService.save(position);
    }

    @GetMapping("/getAll")
    public List<Position> getAll(){
        return positionService.getAll();
    }

    @PostMapping("/delete")
    public void delete(@RequestBody @NonNull Long id){
        positionService.deleteById(id);
    }

    @GetMapping("/get/{id}")
    public Position get(@NonNull @PathVariable Long id){
        return positionService.get(id);
    }
}
