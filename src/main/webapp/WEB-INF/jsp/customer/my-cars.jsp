<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>My Cars</title>
</head>
<body>
<jsp:include page="customer-menu.jsp"/>
<h2 align="center">Cars Ordered</h2>
<c:choose>
<c:when test="${cars.size() gt 0}"> 
<table id="tablestyle" border="1" cellpadding="1" cellspacing="1" align="center">
	<th></th>
	<th>Plate</th>
	<th>Car Name</th>
	<th>Car Brand</th>
	<th>Reserved Date</th>
	<th>Reservation Until</th>
	<th>Return Date No Pay</th>	
	
	<c:forEach items="${cars}" var="car">
	<tr>
		<td><img width="100" height="100" src="/shop/images/${car.imagePath}"/></td>
		<td>${car.plate}</td>
		<td>${car.carname}</td>
		<td>${car.carbrand}</td>
		<td>${car.bookingStartDate}</td>
		<td>${car.bookingEndDate}</td>
		<td>${car.returnDate}</td>
	</tr>
	</c:forEach>
</table>
</c:when>
<c:otherwise><p align="center">You have no cars in use.</p></c:otherwise>
</c:choose>
</body>
</html>