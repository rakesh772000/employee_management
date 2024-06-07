package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EmployeeServiceImp1 implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		
		return employees;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		Employee savedEmployee=employeeRepository.save(employee);
		return savedEmployee;
	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+employeeId));	
		return employee;
	}

	@Override
	public Employee updateEmployee(Long employeeId, Employee employee) {
	      Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+employeeId));
	      existingEmployee.setFirstName(employee.getFirstName());
	      existingEmployee.setLastName(employee.getLastName());
	      existingEmployee.setEmail(employee.getEmail());   
	     return employeeRepository.save(existingEmployee);
		
	}

	@Override
	public void deleteEmployee(long employeeId) {
		Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+employeeId));
		employeeRepository.deleteById(employeeId);
	}

}
