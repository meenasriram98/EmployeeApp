package com.employee.doa.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.employee.dao.AbstractDBConnection;
import com.employee.dao.EmployeeDao;
import com.employee.domain.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDaoImpl extends AbstractDBConnection implements EmployeeDao {

	public boolean createEmployee(Employee emp) {

		Connection con;
		try {
			con = getConnection();
			PreparedStatement stmt = con.prepareStatement("insert into employee values(?,?,?)");

			String query = "use sample";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			stmt.setString(1, emp.getName());
			stmt.setInt(2, emp.getEmp_id());
			stmt.setInt(3, emp.getManager_id());
			int result = stmt.executeUpdate();
			if (result == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return false;

	}

	public Employee fetchEmployee(String empId) {

		try {
			int id = Integer.parseInt(empId);
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from employee where employee_id=?");

			String query = "use sample";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			stmt.setInt(1, id);
			Employee emp;
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				emp = new Employee(result.getString(1), result.getInt(2), result.getInt(3));
				return emp;

			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public List<Employee> fetchEmployees() {
		try {
			Connection con = getConnection();
			List<Employee> list = new ArrayList<Employee>();
			String query = "use sample";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			String q = "select * from employee";
			rs = st.executeQuery(q);

			while (rs.next()) {
				Employee emp = new Employee(rs.getString(1), rs.getInt(2), rs.getInt(3));
				list.add(emp);
			}
			return list;

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean deleteEmployee(int emp_id) {
		try {
			Connection con = getConnection();
			String query = "use sample";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			PreparedStatement stmt = con.prepareStatement("delete from employee where employee_id=?");
			stmt.setInt(1, emp_id);
			int result = stmt.executeUpdate();

			if (result == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateEmployee(String name, int id, int mid) {
		try {
			Connection con = getConnection();
			String query = "use sample";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			PreparedStatement stmt = con
					.prepareStatement("update employee set employee_name=?,manager_id=? where employee_id=?");
			stmt.setString(1, name);
			stmt.setInt(2, mid);
			stmt.setInt(3, id);
			int result = stmt.executeUpdate();

			if (result == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
