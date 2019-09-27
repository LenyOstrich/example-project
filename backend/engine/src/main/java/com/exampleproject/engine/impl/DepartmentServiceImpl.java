package com.exampleproject.engine.impl;

import com.exampleproject.database.entities.Department;
import com.exampleproject.database.repositories.DepartmentRepository;
import com.exampleproject.engine.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Department save(Department dep) {
        return departmentRepository.save(dep);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department get(Long id) {
        return departmentRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
