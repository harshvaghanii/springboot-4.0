package com.example.homework_week2.services.impl;

import com.example.homework_week2.entities.Department;
import com.example.homework_week2.exceptions.ResourceNotFoundException;
import com.example.homework_week2.repositories.DepartmentRepository;
import com.example.homework_week2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Set<String> DEPARTMENT_FIELDS_ALLOWED_TO_UPDATE = Set.of(
            "title",
            "isActive"
    );


    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID: " + id + " not found!"));
    }

    @Override
    public void deleteDepartmentById(Long id) {
        if (!departmentExists(id)) {
            throw new ResourceNotFoundException("Department with ID: " + id + " not found!");
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartmentById(Long id, Map<String, Object> updates) {
        if (!departmentExists(id)) {
            return null;
        }
        updates.entrySet().removeIf(entry -> !DEPARTMENT_FIELDS_ALLOWED_TO_UPDATE.contains(entry.getKey()));
        Department department = departmentRepository.findById(id).get();

        // Using reflection to perform updates

        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(Department.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, department, value);
        });

        return departmentRepository.save(department);

    }

    public Boolean departmentExists(Long id) {
        return departmentRepository.existsById(id);
    }
}
