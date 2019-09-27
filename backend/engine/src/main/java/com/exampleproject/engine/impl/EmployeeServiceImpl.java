package com.exampleproject.engine.impl;

import com.exampleproject.database.entities.Employee;
import com.exampleproject.database.repositories.EmployeeRepository;
import com.exampleproject.engine.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee empl) {
        return employeeRepository.save(empl);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee get(Long id) {
        return employeeRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
