package com.example.homework_week2.controllers;

import com.example.homework_week2.advices.ApiResponse;
import com.example.homework_week2.dto.DepartmentDto;
import com.example.homework_week2.entities.Department;
import com.example.homework_week2.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        Department toSaveEntity = modelMapper.map(departmentDto, Department.class);
        Department savedEntity = departmentService.createDepartment(toSaveEntity);
        return new ResponseEntity<>(modelMapper.map(savedEntity, DepartmentDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>("The department with ID: " + id + " has been successfully deleted!"));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(modelMapper.map(departmentService.updateDepartmentById(id, updates), DepartmentDto.class));
    }

}
