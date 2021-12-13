package com.example.university.repository;

import com.example.university.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findDepartmentById(Long id);
}
