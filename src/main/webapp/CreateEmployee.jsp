<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.List,java.util.ArrayList,com.employee.domain.Employee"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Employee</title>
</head>
<body>
	<%
		Employee emp = (Employee) request.getAttribute("employee");
		String name = "", id = "", manager_id = "";
		if (emp != null) {
			name = emp.getName();
			id = Integer.toString(emp.getEmp_id());
			manager_id = Integer.toString(emp.getManager_id());
		}
		int a = 55;
	%>
	<form action="createEmployee" method="POST">
		Employee Name<br> <input type="text" id="name" name="name"
			value=<%=name%> required /> <br>Employee id<br> <input
			type="text" id="empid" name="emp_id" value=<%=id%> required
			pattern="[1-9][a-zA-Z0-9]{1,4}" /> <br>Manager id<br> <input
			type="text" id="manid" name="manager_id" value=<%=manager_id%>
			required pattern="[1-9][a-zA-Z0-9]{1,4}" /> <br>
		<input type="submit" value="submit" />
	</form>


	<%-- else
{%>
       
		 Employee Name<br> <input type="text" id="name" name="name" value="<%=emp.getName()%>"/>
<br>     Employee id<br> <input type="text" id="empid" name="emp_id" value="<%=emp.getEmp_id()%>" readonly/>
<br>	 Manager id<br> <input type="text" id="manid" name="manager_id" value="<%=emp.getManager_id()%>"/>

 --%>

</body>
</html>
