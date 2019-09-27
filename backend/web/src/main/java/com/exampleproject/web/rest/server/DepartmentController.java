package com.exampleproject.web.rest.server;

import com.exampleproject.database.entities.Department;
import com.exampleproject.engine.services.DepartmentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public Department save(@RequestBody @NonNull Department department) {
        return departmentService.save(department);
    }

    @GetMapping("/getAll")
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @PostMapping("/delete")
    public void delete(@RequestBody @NonNull Department department){
        departmentService.deleteById(department.getId());
    }

    @GetMapping("/get/{id}")
    public Department get(@NonNull @PathVariable Long id){
        return departmentService.get(id);
    }
}
