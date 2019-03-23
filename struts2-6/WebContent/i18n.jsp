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
   <s:debug></s:debug>
   <s:form action="">
       <!-- 
          label 的方式是 label 写死在标签里 ,但是如果 label 标签使用了 label="%{getText('username')}" 的
                              方式就可以从国际化资源文件中获取 value 值了
                              因为此时在对象栈中有 .DefaultTextProvider 的一个实例，该对象中提供了访问国际化资源文件的 getText() 方法
                              同时还需要通知 struts2 框架 label 中放入的不再是一个普通的字符串，而是一个 ONGL 表达式，所有使用 %{}  把 getText()
                              包装起来，强制进行 OGNL 解析
                              
                              页面上可以直接使用 <s:text name=""/> 标签来访问国际化资源文件里的 value 属性
       -->
       <s:textfield name="username" label="%{getText('username')}"></s:textfield>
       <!-- key 的方式是直接上资源文件中获取 value 值 -->
       <s:textfield name="username" key="username"></s:textfield>
       <s:textfield name="password" key="password"></s:textfield>
       <s:submit name="submit" key="submit"></s:submit>
   </s:form>
</body>
</html>