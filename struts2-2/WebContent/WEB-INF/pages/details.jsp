<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    ProductId : ${productId}
    <br><br>
    ProductName : ${productName}  ^<%=request.getAttribute("productName") %>
    <br><br>
    ProductEesc : ${productDesc}
    <br><br>
    ProductPrice : ${productPrice}
    <br><br>
    <%=request %>
</body>
</html>