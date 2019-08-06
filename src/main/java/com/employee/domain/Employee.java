package com.employee.domain;

public class Employee {

	String name;
	int emp_id;
	int manager_id;

	public Employee(String name, int id, int m_id) {
		this.name = name;
		this.emp_id = id;
		this.manager_id = m_id;
	}

	public String getName() {
		return this.name;
	}

	public int getEmp_id() {
		return this.emp_id;
	}

	public int getManager_id() {
		return this.manager_id;
	}

	public String toString() {
		return "name: " + this.name + "\n" + "Employee_id: " + this.emp_id + "Manager_id: " + this.manager_id;
	}

}
