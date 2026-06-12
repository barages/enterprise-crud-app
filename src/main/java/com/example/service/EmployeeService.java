package com.example.service;

import java.util.*;

import com.example.dto.*;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest r);

    List<EmployeeResponse> getAll();
}