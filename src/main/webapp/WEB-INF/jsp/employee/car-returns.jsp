<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Returns</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Car Returns</h2>
<c:choose>
<c:when test="${cars.size() gt 0}">
<table id="tablestyle" border="1" cellpadding="1" cellspacing="1" align="center">
	<th></th>
	<th>Plate</th>
	<th>Car Name</th>
	<th>Car Brand</th>
	<th>Reserved From</th>
	<th>Reserved Until</th>
	<th>Return Date No Pay</th>
	<th>Reserved By</th>
	<th>Action</th>
	
	<c:forEach items="${cars}" var="car">
	<tr>
		<td><img width="100" height="100" src="/shop/images/${car.key.imagePath}"/></td>
		<td>${car.key.plate}</td>
		<td>${car.key.carname}</td>
		<td>${car.key.carbrand}</td>
		<td>${car.key.bookingStartDate}</td>
		<td>${car.key.bookingEndDate}</td>
		<td>${car.key.returnDate}</td>
		<td>${car.value}</td>
		<td>	
 		<a href="confirm-return.htm?carId=${car.key.carId}&username=${car.value}">Returned By Customer</a>
		</td>
	</tr>
	</c:forEach>

</table>
</c:when>
<c:otherwise><p align="center">There are no cars currently ordered.</p></c:otherwise>
</c:choose>
</body>
</html>