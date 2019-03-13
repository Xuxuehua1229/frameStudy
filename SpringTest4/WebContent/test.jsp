<%@page import="com.test4.spring.struts2.beans.Person"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%
       //1.从 application 域对象中达到 IOC 容器的实例
          ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
       //2.从 IOC 容器中得到  bean
          Person person = (Person)ac.getBean("person");
       //3.使用 bean
          person.hello();
   %>
</body>
</html>