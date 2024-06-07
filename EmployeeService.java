package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee createEmployee(Employee employee);
	Employee getEmployeeById(long employeeId);
	Employee updateEmployee(Long employeeId, Employee employee);
	void deleteEmployee(long employeeId);

}
