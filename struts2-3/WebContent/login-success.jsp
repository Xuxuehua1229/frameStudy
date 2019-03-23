<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   Welcome : ${sessionScope.username}
   <br><br>
   Count On Line : ${applicationScope.count }
   <br><br>
   <a href="logout.do">Logout</a>
</body>
</html>