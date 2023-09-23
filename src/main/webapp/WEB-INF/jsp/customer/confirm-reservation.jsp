<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@page import="java.io.*, java.util.Date, java.util.Enumeration,java.time.format.DateTimeFormatter,java.time.LocalDate" %>  --%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>Confirm Reservation</title>
</head>
<body>
<jsp:include page="customer-menu.jsp"/>
<h2 align="center">Confirm Reservation</h2>

<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr>
	<td>Plate: </td>
	<td>${car.plate}</td>
	
	</tr>
	<tr>
	<td>Car Name: </td>
	<td>${car.carname}</td>
	</tr>
	<tr>
	<td>Car Brand: </td>
	<td>${car.carbrand}</td>
	</tr>
	<tr>
	<td>Reserved Until: </td>
	<td>${bookingEndDate}</td>
	<!-- req scope -->
	</tr>
	<tr>
	<td>Return Date No Pay: </td>
	<td>${returnDate}</td>
	</tr>
</table>
<%-- <form:form modelAttribute="car" method="post">
<form:hidden path="plate" value="${car.getPlate()}"/>
<form:hidden path="carname" value="${car.getCarname()}"/>
<form:hidden path="carbrand" value="${car.getCarbrand()}"/>
<form:hidden path="bookingStartDate" value="${requestScope.bookingStartDate}"/>
<form:hidden path="bookingEndDate" value="${requestScope.bookingEndDate}"/>
<form:hidden path="returnDate" value="${requestScope.returnDate}"/>
<form:hidden path="username" value="${requestScopr.username}"/>
<input type="submit" value="Confirm"/>
</form:form> --%>

<form action="confirm-reservation.htm" method="POST">
	<input type="hidden" name="id" value="${car.getCarId()}"/>
    <input type="hidden" name="plate" value="${car.getPlate()}"/>
	<input type="hidden" name="carname" value="${car.getCarname()}"/>
	<input type="hidden" name="carbrand" value="${car.getCarbrand()}"/>
	<input type="hidden" name="bookingStartDate" value="${bookingStartDate}"/>
	<input type="hidden" name="bookingEndDate" value="${bookingEndDate}"/>
	<input type="hidden" name="returnDate" value="${returnDate}"/>
	<input type="hidden" name="username" value="${username}"/>
	<input type="hidden" name="imagePath" value="${car.getImagePath()}"/>
<p align="center"><input type="submit" value="Confirm"></p>  
</form>

</body>
</html>