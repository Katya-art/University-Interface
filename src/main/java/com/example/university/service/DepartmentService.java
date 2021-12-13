package com.example.university.service;

import com.example.university.entity.Department;

public interface DepartmentService {
    String departmentHead(Department department);
    String departmentStatistic(Department department);
    String averageSalary(Department department);
    String numberOfEmployees(Department department);
    String globalSearch(String template);
    void setHeadToDepartment(Long departmentId, Long lectorId);
}
