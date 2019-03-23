<%@page import="java.util.ArrayList"%>
<%@page import="com.test.struts2.form.actions.City"%>
<%@page import="java.util.List"%>
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
   <% 
      List<City> cities = new ArrayList<City>();
      cities.add(new City(1001,"AA"));
      cities.add(new City(1002,"BB"));
      cities.add(new City(1003,"CC"));
      cities.add(new City(1004,"DD"));
      cities.add(new City(1005,"EE"));
      request.setAttribute("cities", cities);
      
      request.setAttribute("theme", "xhtml"); //可以用来修改局部页面主题
   %>
   <s:debug></s:debug>
   <br><br>
   <!-- 
                    表单标签：
         1.使用和 html 的 form 表单的没什么感觉
         2.Struts2 的 form 标签会生成一个 table , 以进行自动的排版
         3.可以对表单提交的值进行回显：从栈顶对象开始匹配属性，并把匹配的属性值赋到对应的标签 value 中，若栈顶对象没有对应的属性，
                           则依次向下找相应的属性
    -->
   <s:form action="save">
      <s:hidden name="userId"></s:hidden>
      <s:textfield name="userName" label="UserName"></s:textfield>
      <s:password name="password" label="Password"></s:password><!-- showPassword="true" -->
      <s:textarea name="desc" label="Desc"></s:textarea>
      <s:checkbox name="married" label="Married"></s:checkbox>
      <s:radio name="gender" list="#{'1':'Male','0':'Female'}" label="Gender"></s:radio>
      <s:checkboxlist name="city" list="#request.cities" listKey="cityId" 
      listValue="cityName" label="City"></s:checkboxlist>
      <!-- 
          s:optgroup : 可以用作 s:select 的子标签，用于显示更多的下拉框
                              注意：必须有键值对，而不能使用一个集合，让其值既作为 key 值 又作为 value 值
       -->
      <s:select list="{11,12,13,14,15,16,17,18,19,20}" 
          headerKey="" 
          headerValue="请选择"
          name="age"
          label="Age">
          <s:optgroup label="21-30" list="#{21:21}"></s:optgroup>
          <s:optgroup label="31-40" list="#{31:31}"></s:optgroup>
      </s:select>
      <s:submit></s:submit>
   </s:form>
   
   <br><br>
   <s:form action="save" method="post">
      <input type="checkbox" name="married"/>
      <input type="submit" value="submit"/>
   </s:form>
</body>
</html>