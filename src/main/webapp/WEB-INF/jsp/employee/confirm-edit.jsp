<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Confirm Edit</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Edit car Details</h2>

<%-- <table cellpadding="1" cellspacing="1">
	<tr>
	<td>Plate: </td>
	<td>${car.getPlate()}</td>
	</tr>
	<tr>
	<td>CarName: </td>
	<td>${car.getCarname()}</td>
	</tr>
	<tr>
	<td>CarBrand: </td>
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
</table> --%>


<%-- <form style="text-align:left" action="confirm-edit.htm" method="POST">
	<input type="hidden" name="id" value="${car.getCarId()}"/>
    Plate: ${car.getPlate()}
    <br><br>
	Car Name: <input type="text" name="carname" value="${car.getCarname()}" style="font-weight: bold"/>
	<br><br>
	Car Brand: <input type="text" name="carbrand" value="${car.getCarbrand()}"style="font-weight: bold"/>
	<br><br>
	Reserved From: ${car.getBookingStartDate()}
	<br><br>
	Reserved Until: ${car.getBookingEndDate()}
	<br><br>
	Return By: ${car.getReturnDate()}
	<br><br>
	Reserved By: ${car.getReservedByUser().getUsername()}
	<input type="checkbox" id="removeReservation" name="removeReservation" value="removeReservation"> Remove Order
	<br><br>
	Current User: ${car.getTheUser().getUsername()}
	<input type="checkbox" id="removeCurrentUser" name="removeCurrentUser" value="removeCurrentUser"> Remove Current User
	<br><br>
	<input type="submit" value="Edit"> 
</form>
 --%>

<form style="text-align:left" action="confirm-edit.htm" method="POST">
<input type="hidden" name="id" value="${car.getCarId()}"/>
<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr><td>Plate</td><td>${car.getPlate()}</td></tr>
	<tr><td>Car Name:</td><td><input type="text" name="carname" value="${car.getCarname()}" style="font-weight: bold" required="required"/></td></tr>
	<tr><td>Car Brand:</td><td><input type="text" name="carbrand" value="${car.getCarbrand()}"style="font-weight: bold" required="required"/></td></tr>
	<tr><td>Reserved From:</td><td>${car.getBookingStartDate()}</td></tr>
	<tr><td>Reserved Until:</td><td>${car.getBookingEndDate()}</td></tr>
	<tr><td>Return By:</td><td>${car.getReturnDate()}</td></tr>
	<tr><td>Reserved By:</td><td>${car.getReservedByUser().getUsername()}</td>
	<td><input type="checkbox" id="removeReservation" name="removeReservation" value="removeReservation"> Remove Reservation</td>
	</tr>
	<tr><td>Current User:</td><td>${car.getTheUser().getUsername()}</td>
	<td><input type="checkbox" id="removeCurrentUser" name="removeCurrentUser" value="removeCurrentUser"> Remove Current User</td>
	</tr>
	<tr>
	<td>
	<input style="width:100px"  type="submit" value="Edit"> 
	</td>
	</tr>
</table>
</form>
 



</body>
</html>