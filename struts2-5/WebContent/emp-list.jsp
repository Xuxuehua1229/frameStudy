<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: 1px solid;
		border-collapse: collapse;
	}
	
	td {
		border: 1px solid;
	}
</style>
</head>
<body>
    <s:form action="emp-save" method="post"> 
       <s:hidden name="employeeId" label="EmployeeId"></s:hidden>
       <s:textfield name="firstName" label="FirstName"></s:textfield>
       <s:textfield name="lastName" label="LastName"></s:textfield>
       <s:textfield name="email" label="email"></s:textfield>
       <s:submit></s:submit>
    </s:form>
    <br><hr><br>
	<table border="1">
		<thead>
			<tr>
				<td>ID</td>
				<td>FirstName</td>
				<td>LastName</td>
				<td>Email</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.emps">
				<tr>
					<td>${employeeId}</td>
					<td>${firstName}</td>
					<td>${lastName}</td>
					<td>${email}</td>
					<td><a href="emp-edit.action?employeeId=${employeeId}">Edit</a></td>
					<td><a href="emp-delete.action?employeeId=${employeeId}">Delete</a></td>
				</tr>
           </s:iterator>
		</tbody>
	</table>
</body>
</html>