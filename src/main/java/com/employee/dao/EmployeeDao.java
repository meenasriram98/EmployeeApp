package com.employee.dao;

import java.util.List;

import com.employee.domain.Employee;

public interface EmployeeDao {
	boolean createEmployee(Employee emp);

	Employee fetchEmployee(String empId);

	List<Employee> fetchEmployees();

	boolean deleteEmployee(int emp_id);

	boolean updateEmployee(String name, int id, int mid);
}