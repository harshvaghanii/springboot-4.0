package com.example.homework_week2.services;

import com.example.homework_week2.entities.Employee;

public interface EmployeeService {

    public Employee getEmployeeById(Long id);

    public Employee createEmployee(Employee employee);

}
