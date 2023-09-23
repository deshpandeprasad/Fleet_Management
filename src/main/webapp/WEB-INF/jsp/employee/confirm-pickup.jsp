<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Confirm Pickup</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Confirm Pickup</h2>

<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr>
	<td>Plate: </td>
	<td>${car.getPlate()}</td>
	</tr>
	<tr>
	<td>Car Name: </td>
	<td>${car.getCarname()}</td>
	</tr>
	<tr>
	<td>Car Brand: </td>
	<td>${car.getCarbrand()}</td>
	</tr>
	<tr>
	<td>Reserved From: </td>
	<td>${car.getBookingStartDate()}</td>
	</tr>
	<tr>
	<td>Reserved Until: </td>
	<td>${car.getBookingEndDate()}</td>
	</tr>
	<tr>
	<td>Return Date No Pay: </td>
	<td>${car.getReturnDate()}</td>
	</tr>
	<tr>
	<td>Reserved By: </td>
	<td>${car.getReservedByUser().getUsername()}</td>
	</tr>
</table>
<form action="confirm-pickup.htm" method="POST">
	<input type="hidden" name="id" value="${car.getCarId()}"/>
    <input type="hidden" name="plate" value="${car.getPlate()}"/>
	<input type="hidden" name="carname" value="${car.getCarname()}"/>
	<input type="hidden" name="carbrand" value="${car.getCarbrand()}"/>
	<input type="hidden" name="bookingStartDate" value="${car.getBookingStartDate()}"/>
	<input type="hidden" name="bookingEndDate" value="${car.getBookingEndDate()}"/>
	<input type="hidden" name="returnDate" value="${car.getReturnDate()}"/>
	<input type="hidden" name="username" value="${car.getReservedByUser().getUsername()}"/>
<p align="center"><input type="submit" value="Confirm"></p>
</form>


</body>
</html>