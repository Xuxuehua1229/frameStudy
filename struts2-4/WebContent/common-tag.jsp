<%@page import="com.test.struts2.valuestack.PersonComparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.test.struts2.valuestack.Person"%>
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
   <s:debug></s:debug>
   <br>
   ① s:property : 打印值栈中的属性值: 对于对象栈，打印值栈中的属性值。
   <br><br>
   <s:property value="productName"/>
   <br><br>
         对于 Map 栈，打印 request、session、application 的某个属性 或 某个请求参数的值
   <br><br>
   <s:property value="#attr.date"/>
   <br>
    <!-- {name2=test2, name1=test1}  -->
   <s:property value="#parameters.name[0]"/><%--  - <s:property value="#parameters.name2[0]"/> --%>
   <br><br>
   ② s:url : 创建一个 URL 的字符串
   <br><br>
   <s:url value="/getProduct" var="url">
       <!-- 指定 URL 包含的请求参数，2002 不可能是一个属性名，struts2 把2002直接作为属性值 -->
       <s:param name="productId" value="2001"></s:param>
   </s:url> 
   ${url}
   <br><br>
   <s:url value="/getProduct" var="url2">
       <!-- 对于 value 值会自动的进行 OGNL 解析！ -->
       <s:param name="productId" value="productId"></s:param>
       <s:param name="date" value="#session.date"></s:param>
   </s:url>
   ${url2}
   <br><br>
   <s:url value="/getProduct" var="url3">
       <!-- 对于 value 值会自动的进行 OGNL 解析！若不希望进行 OGNL 解析，则使用单引号引起来 ! -->
       <s:param name="productId" value="'abcde'"></s:param>
   </s:url>
   ${url3}
   <br><br>
   <!-- 构建一个请求 action 的地址 -->
   <s:url action="testAction" namespace="/helloWorld" method="save" var="url4"></s:url>
   ${url4}
   <br><br>
   <!-- includeParams="get" 或 includeParams="all"-->
   <s:url value="testUrl" var="url5" includeParams="all"></s:url>
   ${url5 }
   <br><br>
   <!-- 
                   注意： 2.5.18 版本的struts2 中的 set标签的属性
       ① name（已经过时），id（已过时）, var 用来定义变量名
       ② value用来赋值新变量的值，如果没有指定value的值，则将valueStack栈顶的值赋给新的变量，value属性的类型为Object。
                     所以value值为字符串的时候要特别注意加单引号，即(value="'lkk'")，其中lkk就是字符串自动进行 OGNL 解析
       ③ scope属性，默认值为action，可选值为page，session，request，application和action，类型为String，用来指定范围
                     如果scope取值为默认值action，value属性的值将同时保存到request和Stack Context中。其实总结一点就是用scope定义一个范围，
                     然后到相应的范围中去取值，如果没有定义范围，就到request和Stack Context中去取值
   -->
   ③ s:set : 向 page,request,session,application 域对象中加入一个属性
   <s:set var="productName" value="'testTagName'"  scope="page"></s:set>
   <br><br>
   ${pageScope.productName}
   <br><br>
   ④ s:push : 把一个对象在标签开始后压入到值栈中，标签结束时，弹出值栈
   <br><br>
   <%
      Person person = new Person();
      person.setName("zhangsan");
      person.setAge(22);
      request.setAttribute("person", person);
   %>
   <s:push value="#request.person">
      ${name}
   </s:push>
   <br>
   --- ${name} ---
   <br><br>
   ⑤ s:if , s:else , s:elseif:
   <br><br>
   <!-- 可以直接使用值栈中的属性 -->
   <s:if test="productPrice > 1000">
       I7 浏览器
   </s:if>
   <s:elseif test="productPrice > 800">
       I5 浏览器
   </s:elseif>
   <s:else>
       I3 浏览器
   </s:else>
   <br>
   <s:if test="#request.person.age > 10">
                      大于10岁！
   </s:if>
   <s:else>
                      小于 10 岁 或 等于 10 岁！
   </s:else>
   <br><br>
   ⑥ s:iterator: 遍历集合的  把这个可遍历对象里的每一个元素依次压入和弹出
   <br><br>
   <%
      List<Person> persons = new ArrayList<>();
      persons.add(new Person("zhangsan",22));
      persons.add(new Person("lisi",21));
      persons.add(new Person("wanwu",19));
      persons.add(new Person("lisa",12));
      persons.add(new Person("hahaha",30));
      request.setAttribute("persons", persons);
   %>
   <s:iterator value="#request.persons" status="status">
      index:${status.index} - count:${status.count} - name:${name} - age:${age}
      <br>
   </s:iterator>
   <br><br>
    ---  ${name} - ${age} ---
    <br>
    <s:iterator value="persons">
         ${name} - ${age}
    </s:iterator>
    <br><br>
    ⑦ s:sort : 可以对集合总的元素进行排序
    <br><br>
    <%
        PersonComparator pc = new PersonComparator();
        request.setAttribute("comparator", pc);
    %>
    <s:sort comparator="#request.comparator" source="persons" var="persons2"></s:sort>
    <s:iterator value="#attr.persons2">
        ${name} -> ${age} <br>
    </s:iterator>
    <br><br>
    ⑧ s:date : 可以对 Date 对象进行排版
    <br><br>
    <s:date name="#session.date" format="yyyy-MM-dd HH:mm:ss" var="date1"></s:date>
    date : ${date1}
    <br><br>
    ⑨ s:a 可以使用 %{} 把属性包装起来，使其强制进行的 OGNL 解析
    <s:iterator value="persons">
       <s:a href="getPerson?name=%{name}">${name}</s:a>
    </s:iterator>
</body>
</html>