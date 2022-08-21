package com.learn.learn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.learn.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	Employee findByName(String name);

	

}
