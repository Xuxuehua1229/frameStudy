<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    ProductId : ${requestScope.product.productId}
    <br><br>
    ProductName : ${requestScope.product.productName}
    <br><br>
    ProductEesc : ${requestScope.product.productDesc}
    <br><br>
    ProductPrice : ${requestScope.product.productPrice}
    <br><br>
</body>
</html>