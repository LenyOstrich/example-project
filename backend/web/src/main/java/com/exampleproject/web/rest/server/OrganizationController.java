package com.exampleproject.web.rest.server;

import com.exampleproject.database.entities.Organization;
import com.exampleproject.engine.services.OrganizationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/save")
    public Organization save(@RequestBody @NonNull Organization org) {
        return organizationService.save(org);
    }

    @GetMapping("/getAll")
    public List<Organization> getAll(){
        List<Organization> list = organizationService.getAll();
        return list;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody @NonNull Organization org){
        organizationService.deleteById(org.getId());
    }

    @GetMapping("/get/{id}")
    public Organization get(@NonNull @PathVariable Long id){
        return organizationService.get(id);
    }
}
