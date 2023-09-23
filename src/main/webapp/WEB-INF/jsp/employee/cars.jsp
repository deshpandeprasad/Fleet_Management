<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>cars</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">cars</h2>
<p align="center"><a href="add-cars.htm">Add cars</a></p>
<br><br>
<c:choose>
<c:when test="${cars.size() gt 0}">
<table id="tablestyle" align="center" border="1" cellpadding="1" cellspacing="1">
	<th></th>
	<th>Plate</th>
	<th>Car Name</th>
	<th>Car Brand</th>
	<th>Reserved From</th>
	<th>Reserved Until</th>
	<th>Return Date No Pay</th>
	<th>Reserved By</th>
	<th>Ordered by</th>
	<th>Action</th>
	

	<c:forEach items="${cars}" var="car">
	<tr>
		<td><img width="100" height="100" src="/shop/images/${car.imagePath}"/></td>
		<td>${car.plate}</td>
		<td>${car.carname}</td>
		<td>${car.carbrand}</td>
		<td>${car.bookingStartDate}</td>
		<td>${car.bookingEndDate}</td>
		<td>${car.returnDate}</td>
		<td>${car.getReservedByUser().getUsername()}</td>
		<td>${car.getTheUser().getUsername()}</td>
		<td><a href="confirm-edit.htm?carId=${car.carId}">Edit</a>
		<a href="confirm-delete.htm?carId=${car.carId}">Delete</a>
		</td>
	</tr>
	</c:forEach>
	

</table>
</c:when>
<c:otherwise><p align="center">There are no cars in the catalog. Click Add cars to add cars.</c:otherwise>
</c:choose>
</body>
</html>