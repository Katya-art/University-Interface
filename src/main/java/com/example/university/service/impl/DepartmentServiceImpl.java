package com.example.university.service.impl;

import com.example.university.entity.Degree;
import com.example.university.entity.Department;
import com.example.university.entity.Lector;
import com.example.university.repository.DepartmentRepository;
import com.example.university.repository.LectorRepository;
import com.example.university.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final LectorRepository lectorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
        this.lectorRepository = lectorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String departmentHead(Department department) {
        return String.format("Head of %s is %s %s", department.getName(), department.getHead().getFirstName(),
                department.getHead().getLastName());
    }

    @Override
    public String departmentStatistic(Department department) {
        return String.format("assistance: %s \nassociate professors: %s \nprofessors: %s",
                lectorRepository.countAllByDepartmentsAndDegree(department, Degree.Assistant),
                lectorRepository.countAllByDepartmentsAndDegree(department, Degree.Associate_Professor),
                lectorRepository.countAllByDepartmentsAndDegree(department, Degree.Professor));
    }

    @Override
    public String averageSalary(Department department) {
        double salary = 0;
        for (Lector lector : lectorRepository.findAllByDepartments(department)) {
            salary += lector.getSalary();
        }
        return String.format("The average salary of %s is %s", department.getName(),
                salary/lectorRepository.countAllByDepartments(department));
    }

    @Override
    public String numberOfEmployees(Department department) {
        return String.format("The number of employees is %s", lectorRepository.countAllByDepartments(department));
    }

    @Override
    public String globalSearch(String template) {
        StringBuilder result = new StringBuilder();
        for (Lector lector : lectorRepository.findAllByFirstNameContainingOrLastNameContaining(template, template)) {
            result.append(lector.getFirstName()).append(" ").append(lector.getLastName()).append("\n");
        }
        return String.valueOf(result);
    }

    @Override
    public void setHeadToDepartment(Long departmentId, Long lectorId) {
        Department department = departmentRepository.findDepartmentById(departmentId);
        department.setHead(lectorRepository.findLectorById(lectorId));
        departmentRepository.save(department);
    }
}
