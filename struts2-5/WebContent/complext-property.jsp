<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <s:form action="testComplextProperty">
       <s:textfield name="depatName" label="DepatName"></s:textfield>
       <!-- 映射属性的属性 -->
       <s:textfield name="manager.name" label="ManagerName"></s:textfield>
       <s:textfield name="manager.birth" label="ManagerBirth"></s:textfield>
       <s:submit></s:submit>
   </s:form>
</body>
</html>