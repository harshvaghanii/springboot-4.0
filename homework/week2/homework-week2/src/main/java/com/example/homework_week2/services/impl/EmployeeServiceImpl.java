package com.example.homework_week2.services.impl;

import com.example.homework_week2.entities.Employee;
import com.example.homework_week2.exceptions.ResourceNotFoundException;
import com.example.homework_week2.repositories.EmployeeRepository;
import com.example.homework_week2.services.EmployeeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with ID: " + id + " not found!"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


}
