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
        Department d = new Department();
        d.setId(r.department().id());
        d.setName(r.department().name());
        d.setDescription(r.department().description());
        e.setDepartment(d);

        e.setSalary(r.salary());
        e = repo.save(e);
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