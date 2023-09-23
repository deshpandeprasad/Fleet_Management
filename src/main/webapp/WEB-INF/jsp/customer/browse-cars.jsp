<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>Browse Cars</title>
</head>
<body>
<jsp:include page="customer-menu.jsp"/>
<h2 align="center">Browse Cars</h2>
<c:if test="${usercars.size() eq 3}">
<p style="color:red" align="center">Cannot reserve more cars since you already have 3 cars reserved/in use.</p>
</c:if>
<c:choose>
<c:when test="${cars.size() gt 0}">
<table id="tablestyle" border="1" cellpadding="1" cellspacing="1" align="center">
	<th></th>
	<th>Plate</th>
	<th>Car Name</th>
	<th>Car Brand</th>
	<th>Return Date No Pay</th>
	<th>Reserved Until</th>
	<th>Action</th>
	
	
	<c:forEach items="${cars}" var="car">
	<tr>
		<td><img width="100" height="100" src="/shop/images/${car.imagePath}"/></td>
		<td>${car.plate}</td>
		<td>${car.carname}</td>
		<td>${car.carbrand}</td>
		<td>${car.returnDate}</td>
		<td>${car.bookingEndDate}</td>
		<td>	
 		<c:choose>
		<c:when test= "${car.bookingEndDate != null || car.returnDate !=null}">Reserved</c:when>
		<c:when test="${usercars.size() eq 3}">
		</c:when>
		<c:otherwise>
		<a href="confirm-reservation.htm?carId=${car.carId}&username=${sessionScope.username}">Select</a>
		</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</c:forEach>
</table>
</c:when>
	<c:otherwise>
	<p align="center">There are no cars available.</p>
	</c:otherwise>
</c:choose>
</body>
</html>