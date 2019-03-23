<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
    <%-- ^<%=request.getAttribute("productName") %> --%>
    <s:debug></s:debug>
    <br><br>
    ProductName : ${productName}   ^<s:property value="[0].productName"/>  ^^<s:property value="[1].productName"/>
    <br><br>
    ProductEesc : ${productDesc}
    <br><br>
    ProductPrice : ${productPrice}
    <br><br>
    ProductPrice : <s:property value="[0].productPrice"/>
    <br><br>
    ProductPrice : <s:property value="productPrice"/>
    <br><br>
    ProductName1 : ${sessionScope.product.productName }
    <s:property value="#session.product.productName"/>
    <br><br>
    ProductName2: ${requestScope.test.productName }
    <s:property value="#request.test.productName"/>
    <br><br>
    <!-- 使用 OGNL 调用 public 类的 public 类型的静态字段和静态方法  -->
    <s:property value="@java.lang.Math@PI"/>
    <br><br>
    <s:property value="@java.lang.Math@cos(0)"/>
    <br><br>
    <s:property value="productName"/>
    <!-- 调用对象栈的方法为一个属性赋值 -->
    <s:property value="setProductName('xixixi')"/>
    <br><br>
    <s:property value="productName"/>
    <!-- 调用数组对象的属性 -->
    <%
       String [] names = new String[]{"aa","bb","cc","dd"};
       request.setAttribute("names", names);
    %>
    <br><br>
    length:<s:property value="#attr.names.length"/>
    <br><br>
    names[2]:<s:property value="#attr.names[2]"/>
    <br><br>
    <%
       Map<String,String> letters = new HashMap<String,String>();
       request.setAttribute("letters", letters);
       
       letters.put("AA", "aa");
       letters.put("BB", "bb");
       letters.put("CC", "cc");
    %>
    <!-- 使用 OGNL 访问 Map -->
    MapSize:<s:property value="#attr.letters.size"/>
    <br><br>
    letters["CC"]:<s:property value="#attr.letters['CC']"/>
</body>
</html>