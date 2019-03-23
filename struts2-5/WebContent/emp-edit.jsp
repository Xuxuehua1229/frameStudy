<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <s:debug></s:debug>
    <br><br>
    <s:form action="emp-update" method="post"> 
       <s:hidden name="employeeId" label="EmployeeId"></s:hidden>
       <s:textfield name="firstName" label="FirstName"></s:textfield>
       <s:textfield name="lastName" label="LastName"></s:textfield>
       <s:textfield name="email" label="Email"></s:textfield>
       <s:submit></s:submit>
    </s:form>
</body>
</html>