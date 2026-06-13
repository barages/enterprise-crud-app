package com.example.service.impl;

import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.dto.*;
import com.example.entity.*;
import com.example.repository.*;
import com.example.service.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repo;
    private final DepartmentRepository departmentRepository;

    public EmployeeResponse create(EmployeeRequest r) {

        Department department = departmentRepository
                .findByName(r.department().name())
                .orElseGet(() -> {
                    Department newDept = new Department();
                    newDept.setName(r.department().name());
                    newDept.setDescription(r.department().description());
                    return departmentRepository.save(newDept);
                });

        Employee employee = new Employee();
        employee.setName(r.name());
        employee.setSalary(r.salary());

        employee.setDepartment(department);

        Employee e = repo.save(employee);
        return new EmployeeResponse(e.getId(), e.getName(), e.getDepartment() != null ? e.getDepartment().getName() : null, e.getSalary());
    }

    public List<EmployeeResponse> getAll() {
        List<EmployeeResponse> list = new ArrayList<>();
        for (Employee e : repo.findAll()) {
            EmployeeResponse employeeResponse = new EmployeeResponse(e.getId(), e.getName(), e.getDepartment() != null ? e.getDepartment().getName() : null, e.getSalary());
            list.add(employeeResponse);
        }
        return list;
    }
}