package com.learn.learn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learn.exception.ResourceNotFoundException;
import com.learn.learn.model.Employee;
import com.learn.learn.repository.EmployeeRepository;



@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository emprepo;
	
	@GetMapping("/emp")
	public List<Employee> getAllEmp()
	{
		return emprepo.findAll();
	}
	
	@PostMapping("/savemp")
	public void SaveEmp(@RequestBody Employee emp)
	{
		emprepo.save(emp);
	}
	
	@PutMapping("/updateemp/{id}")
	public ResponseEntity<Employee> updateEmp(@PathVariable int id,@RequestBody Employee emp)
	
	{
		Employee empd=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"+id));;
		empd.setName(emp.getName());
		empd.setSal(emp.getSal());
		emprepo.save(empd);
		return ResponseEntity.ok(empd);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteByid(@PathVariable int id,@RequestBody  Employee  emp )
	{
		Employee  empd=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"+id));
		emprepo.delete(empd);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/empname/{name}")
	public Employee FindEmpName(@PathVariable String name)
	{
		return emprepo.findByName(name);
	}
	
	
}
