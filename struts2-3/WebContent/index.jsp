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
   <a href="TestActionContext.action?name=helloworld&name=hahahaha">Test ActionContext</a>
   <br><br>
   <a href="TestAwareAction.action?name=helloworld">Test AwareAction</a>
   <br><br>
   <a href="TestServletActionContext.action">Test ServletAction</a>
   <br><br>
   <a href="TestServletAwareAction.action">Test ServletAwareAction</a>
   <br><br>
   <a href="Login_ui.do">LoginUI</a>
   <br><br>
   <a href="testActionSupport">Test ActionSupport</a>
   <br><br>
   <a href="testResultAction.do?number=7">Test ResultAction</a>
   <br><br>
   <a href="testDynamicAction.do">Test DynamicAction</a>
   <%
      if(application.getAttribute("date") == null){
    	  application.setAttribute("date", new Date());
      }
   %>
</body>
</html>