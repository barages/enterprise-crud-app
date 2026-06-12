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

    public EmployeeResponse create(EmployeeRequest r) {
        Employee e = new Employee();
        e.setName(r.name());
        // Map incoming DepartmentDto to the Deparment entity instance.
        // We don't perform a DB lookup here; populate the entity with provided values.
        if (r.department() != null) {
            Deparment d = new Deparment();
            d.setId(r.department().id());
            d.setName(r.department().name());
            d.setDescription(r.department().description());
            e.setDepartment(d);
        } else {
            e.setDepartment(null);
        }
        e.setSalary(r.salary());
        e = repo.save(e);
        return new EmployeeResponse(e.getId(), e.getName(), e.getDepartment() != null ? e.getDepartment().getName() : null, e.getSalary());
    }

    public List<EmployeeResponse> getAll() {
        return repo.findAll().stream().map(e -> new EmployeeResponse(e.getId(), e.getName(), e.getDepartment() != null ? e.getDepartment().getName() : null, e.getSalary())).collect(Collectors.toList());
    }
}