package com.example.homework_week2.controllers;

import com.example.homework_week2.dto.EmployeeDTO;
import com.example.homework_week2.entities.Employee;
import com.example.homework_week2.services.EmployeeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apis/employees")
@RequiredArgsConstructor
@Getter
@Setter
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeID) {

        Employee employee = employeeService.getEmployeeById(employeeID);
        return ResponseEntity.ok(modelMapper.map(employee, EmployeeDTO.class));

    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(modelMapper.map(savedEmployee, EmployeeDTO.class), HttpStatus.CREATED);
    }

}
