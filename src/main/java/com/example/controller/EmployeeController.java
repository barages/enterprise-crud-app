package com.example.controller;

import java.util.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.dto.*;
import com.example.service.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService s;

    @PostMapping
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest r) {
        return s.create(r);
    }

    @GetMapping
    public List<EmployeeResponse> all() {
        return s.getAll();
    }
}