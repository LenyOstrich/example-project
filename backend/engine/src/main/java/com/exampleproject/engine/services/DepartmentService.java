package com.exampleproject.engine.services;

import com.exampleproject.database.entities.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> getAll();
    public Department save(Department org);
    public void deleteById (Long id);
    public Department get(Long id);
}
