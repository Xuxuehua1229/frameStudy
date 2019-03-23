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
   <s:form action="emp-details">
       <s:textfield name="name" label="Name"></s:textfield>
       <s:password name="password" label="Password"></s:password>
       <s:radio name="gender" list="#{'1':'Male','0':'Female'}" label="Gender"></s:radio>
       <s:select name="department" list="#request.departments" listKey="deptId" listValue="deptName"
       label="Department"></s:select>
       <s:checkboxlist name="roles" list="#request.roles" listKey="roleId" listValue="roleName"
       label="Role"></s:checkboxlist>
       <s:textarea name="desc" label="Desc"></s:textarea>
       <s:submit></s:submit>
   </s:form>
</body>
</html>