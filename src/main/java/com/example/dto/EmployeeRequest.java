package com.example.dto;

import jakarta.validation.constraints.*;

/**
 * Request DTO for creating/updating an Employee.
 * Replaces the previous plain String department with a DepartmentDto so callers
 * can send structured department data (id, name, description).
 */
public record EmployeeRequest(@NotBlank String name, @NotNull DepartmentDto department, @Positive Double salary) {
}