<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h4> Test ActionContext Page</h4>
   
   application : ${applicationScope.applicationKey}
   <br><br>
   session : ${sessionScope.sessionKey}
   <br><br>
   request : ${requestScope.requestKey}
</body>
</html>