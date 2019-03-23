<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <a href="userAction-save">User Save</a>
   <br><br>
   <a href="userAction-modify">User Update</a>
   <br><br>
   <a href="userAction-delete">User Delete</a>
   <br><br>
   <a href="userAction-select">User Query</a>
   <br><br>
   <a href="awareAction">AwareAction</a>
   <br><br>
   <!-- <a href="testTag?name1=test1&name2=test2">Test Common-Tag</a> -->
   <a href="testTag?name=test">Test Common-Tag</a>
   <br><br>
   
   <%
      session.setAttribute("date", new Date());
   %>
   <form action="testTag.action" method="post">
      <input type="text" name="username"/>
      <input type="submit" value="submit"/>
   </form>
</body>
</html>