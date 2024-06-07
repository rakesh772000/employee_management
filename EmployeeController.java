package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		Employee employeeCreated = employeeService.createEmployee(employee);
		return new ResponseEntity<>(employeeCreated,HttpStatus.CREATED);
	}
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable("id")Long employeeId){
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id")Long employeeId, @RequestBody Employee employee){
		Employee updateEmployee = employeeService.updateEmployee(employeeId, employee);
		return ResponseEntity.ok(updateEmployee);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee deleted successfully");
	}
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> emplyeesList = employeeService.getAllEmployees();
		return ResponseEntity.ok(emplyeesList);
	}

}
