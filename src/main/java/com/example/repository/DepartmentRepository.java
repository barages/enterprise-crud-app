package com.example.repository;

import com.example.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Double> {

      Optional<Department> findByName(String name);
}
