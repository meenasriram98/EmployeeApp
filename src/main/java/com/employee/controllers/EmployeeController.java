package com.employee.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.dao.EmployeeDao;
import com.employee.doa.impl.EmployeeDaoImpl;
import com.employee.domain.Employee;
import com.employee.domain.Error;

public class EmployeeController extends HttpServlet {

	EmployeeDao dao;
	Error e;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		processEmployee(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processEmployee(request, response);
	}

	public void processEmployee(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getServletPath();
		System.out.println(path);
		String list = "/list";
		String create = "/createEmployee";
		String delete = "/delete";
		String edit = "/edit";
		if (path.equals(list)) {
			printList(request, response);
		} else if (path.equals(create)) {
			createEmployee(request, response);
		} else if (path.equals(delete)) {
			deleteEmployee(request, response);
		} else if (path.equals(edit)) {
			editEmployee(request, response);
		} else {
			showError(request, response);
		}
	}

	private void showError(HttpServletRequest request, HttpServletResponse response) {
		String message = e.getPathError();
		request.setAttribute("error", message);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void editEmployee(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Employee employee = dao.fetchEmployee(id);
		request.setAttribute("employee", employee);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreateEmployee.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("delete controller");
		String user = request.getParameter("user");
		System.out.println("deleting user" + user);

		boolean result = dao.deleteEmployee(Integer.parseInt(user));

		if (result) {
			try {
				response.sendRedirect("/EmployeeWebApp/list");
			} catch (IOException e) {
				e.printStackTrace();
				// redirect to error.jsp
			}
		} else {
			System.out.println("employee could not be deleted");
		}
	}

	private void createEmployee(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String e_id = request.getParameter("emp_id");
		String m_id = request.getParameter("manager_id");
		System.out.println(name);
		System.out.println(e_id);
		System.out.println(m_id);

		if (name == null || e_id == null || m_id == null) {
			Employee emp = new Employee(name, Integer.parseInt(e_id), Integer.parseInt(m_id));
			request.setAttribute("employee", emp);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreateEmployee.jsp");
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException se) {
				se.printStackTrace();
			} catch (IOException se) {
				se.printStackTrace();
			}
		}

		Employee employee = dao.fetchEmployee(e_id);

		if (employee == null) {
			Employee emp = new Employee(name, Integer.parseInt(e_id), Integer.parseInt(m_id));

			boolean result = dao.createEmployee(emp);

			if (result) {
				System.out.println("created employee");
				String message = "employee created";
				request.setAttribute("success", message);
				printList(request, response);
			} else {
				String error = e.getCreateError();
				request.setAttribute("error", error);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
				try {
					requestDispatcher.forward(request, response);
				} catch (ServletException se) {
					se.printStackTrace();
				} catch (IOException se) {
					se.printStackTrace();
				}
				System.out.println("could not create employee");
			}
		} else {
			boolean result = dao.updateEmployee(name, Integer.parseInt(e_id), Integer.parseInt(m_id));

			if (result) {
				System.out.println("updated employee");
				String message = "employee created";
				request.setAttribute("success", message);
				printList(request, response);
			} else {
				request.setAttribute("error", e.getUpdateError());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
				try {
					requestDispatcher.forward(request, response);
				} catch (ServletException se) {
					se.printStackTrace();
				} catch (IOException se) {
					se.printStackTrace();
				}
				System.out.println("could not update employee");
			}
		}

	}

	private void printList(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> list = dao.fetchEmployees();
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("EmployeesList.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException se) {
			se.printStackTrace();
		} catch (IOException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		System.out.println("servlet started");
		dao = new EmployeeDaoImpl();
		e = new Error();

	}

	@Override
	public void destroy() {
	}

}
