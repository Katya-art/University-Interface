package com.example.university.repository;

import com.example.university.entity.Degree;
import com.example.university.entity.Department;
import com.example.university.entity.Lector;
import org.springframework.data.repository.CrudRepository;

public interface LectorRepository extends CrudRepository<Lector, Long> {
    Lector findLectorById(Long id);
    Iterable<Lector> findAllByDepartments(Department department);
    Integer countAllByDepartmentsAndDegree(Department department, Degree degree);
    Integer countAllByDepartments(Department department);
    Iterable<Lector> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
