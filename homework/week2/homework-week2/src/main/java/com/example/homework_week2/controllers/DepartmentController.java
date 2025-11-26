package com.example.homework_week2.controllers;

import com.example.homework_week2.dto.DepartmentDto;
import com.example.homework_week2.entities.Department;
import com.example.homework_week2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final ModelMapper modelMapper;
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments().stream().map((element) -> modelMapper.map(element, DepartmentDto.class)).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(departmentService.getDepartmentById(id), DepartmentDto.class));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        Department toSaveEntity = modelMapper.map(departmentDto, Department.class);
        Department savedEntity = departmentService.createDepartment(toSaveEntity);
        return new ResponseEntity<>(modelMapper.map(savedEntity, DepartmentDto.class), HttpStatus.CREATED);
    }

}
