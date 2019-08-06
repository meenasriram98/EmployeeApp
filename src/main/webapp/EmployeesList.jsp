<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees List</title>
</head>
<body>
	<%@ page
		import="java.util.List,java.util.ArrayList,com.employee.domain.Employee"%>
	<br>
	<a href="CreateEmployee.jsp">New</a>

	<table>
		<tr>
			<th>Name</th>
			<th>Employee id</th>
			<th>manager id</th>
		</tr>
		<%
			ArrayList<Employee> list = (ArrayList<Employee>) request.getAttribute("list");
		    String success=(String)request.getAttribute("success");
		    if(success!=null)
		    {
		    	out.println(success);
		    }

			for (Employee e : list) {
		%>
		<tr>
			<td>
				<%
					out.print(e.getName());
				%>
			</td>
			<td>
				<%
					out.print(e.getEmp_id());
				%>
			</td>
			<td>
				<%
					out.print(e.getManager_id());
				%>
			</td>
			<form action="delete" method="post">
				<td>
					<%
						int id = e.getEmp_id();
					%> <input type="hidden" name="user" value="<%=id%>" /> <input
					type="submit" name="<%=id%>" value="Delete" />
				</td>
			</form>
			<form action="createEmployee" method="post">
				<td><a href="edit?id=<%=e.getEmp_id()%>">Edit</a></td>
			</form>
		</tr>
		<%} %>
	</table>
</body>