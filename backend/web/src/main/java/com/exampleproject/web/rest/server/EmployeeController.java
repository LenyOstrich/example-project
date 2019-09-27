package com.exampleproject.web.rest.server;

import com.exampleproject.database.entities.Employee;
import com.exampleproject.engine.services.EmployeeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Employee save(@RequestBody @NonNull Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @PostMapping("/delete")
    public void delete(@RequestBody @NonNull Long id){
        employeeService.deleteById(id);
    }

    @GetMapping("/get/{id}")
    public Employee get(@NonNull @PathVariable Long id){
        return employeeService.get(id);
    }
}
