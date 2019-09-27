package com.exampleproject.engine.services;

import com.exampleproject.database.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAll();
    public Employee save(Employee org);
    public void deleteById (Long id);
    public Employee get(Long id);
}
