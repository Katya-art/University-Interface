package com.example.university;

import com.example.university.entity.Degree;
import com.example.university.entity.Department;
import com.example.university.entity.Lector;
import com.example.university.repository.DepartmentRepository;
import com.example.university.repository.LectorRepository;
import com.example.university.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class UniversityApplication {

	private static final Logger log = LoggerFactory.getLogger(UniversityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(LectorRepository lectorRepository, DepartmentRepository departmentRepository,
								  DepartmentService departmentService) {
		return (args) -> {
			// save a few departments
			departmentRepository.save(new Department("Department of Computing Engineering"));
			departmentRepository.save(new Department("Department of Informatics and Software Engineering"));
			departmentRepository.save(new Department("Department of Information Systems and Technologies"));

			//save a few lectors
			lectorRepository.save(new Lector("Mariana", "Greenwood", 10_000, Degree.Assistant,
					new HashSet<>(Arrays.asList(departmentRepository.findDepartmentById(1L),
							departmentRepository.findDepartmentById(2L)))));
			lectorRepository.save(new Lector("Callen", "Berry", 10_000, Degree.Assistant,
					new HashSet<>(Arrays.asList(departmentRepository.findDepartmentById(2L),
							departmentRepository.findDepartmentById(3L)))));
			lectorRepository.save(new Lector("Ellie-May", "Rayner", 15_000, Degree.Associate_Professor,
					new HashSet<>(Arrays.asList(departmentRepository.findDepartmentById(1L),
							departmentRepository.findDepartmentById(2L)))));
			lectorRepository.save(new Lector("Brielle", "Becker", 15_000, Degree.Associate_Professor,
					new HashSet<>(Arrays.asList(departmentRepository.findDepartmentById(2L),
							departmentRepository.findDepartmentById(3L)))));
			lectorRepository.save(new Lector("Cosmo", "Herbert", 20_000, Degree.Professor,
					new HashSet<>(Arrays.asList(departmentRepository.findDepartmentById(1L),
							departmentRepository.findDepartmentById(2L)))));
			lectorRepository.save(new Lector("Fabio", "Ramirez", 20_000, Degree.Professor,
					new HashSet<>(Arrays.asList(departmentRepository.findDepartmentById(2L),
							departmentRepository.findDepartmentById(3L)))));

			// set head to departments
			departmentService.setHeadToDepartment(1L, 5L);
			departmentService.setHeadToDepartment(2L, 4L);
			departmentService.setHeadToDepartment(3L, 6L);

			// get heads of departments
			log.info("Departments info:");
			log.info("-------------------------------");
			for (Department department : departmentRepository.findAll()) {
				log.info(department.getName());
				log.info(departmentService.departmentHead(department));
				log.info(departmentService.departmentStatistic(department));
				log.info(departmentService.averageSalary(department));
				log.info(departmentService.numberOfEmployees(department));
				log.info("-------------------------------");
			}
			log.info("");

			// Global search by template
			log.info("Global search by template:");
			log.info("--------------------------------");
			log.info(departmentService.globalSearch("ll"));
		};
	}


}
