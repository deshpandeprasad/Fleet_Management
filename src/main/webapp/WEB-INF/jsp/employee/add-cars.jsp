<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Add Car</title>
</head>
<body>
<jsp:include page="employee-menu.jsp"/>
<h2 align="center">Add Car</h2>
<p align="center" style="color:red">${plateExistsErr}</p>
<%-- <form:form modelAttribute="car" method="post">
	Plate: <form:input path="plate" required="required" minlength="10" maxlength="10"/>
	<br><br>
	Car Name: <form:input path="carname" required="required"/>
	<br><br>
	Car Brand: <form:input path="carbrand" required="required"/>
	
	<input type="submit" value="Add"/>

</form:form> --%>

<form:form modelAttribute="car" method="post" enctype="multipart/form-data">
<table id="tablestyle" align="center" cellpadding="1" cellspacing="1">
	<tr><td>
	Plate (10-digits): 
	</td><td>
	<form:input pattern="[0-9]{3}[0-9]{3}[0-9]{4}" path="plate" required="required" minlength="10" maxlength="10"/>
	</td></tr>
	<tr><td>
	Car Name: 
	</td><td>
	<form:input path="carname" required="required"/>
	</td></tr>
	<tr><td>
	Car Brand: 
	</td><td>
	<form:input path="carbrand" required="required"/>
	</td></tr>
	<tr><td>
    Select Item Photo:
    </td><td>
    <input type="file" name="imageFile" accept="image/*" required="required"/>
    </td></tr>
</table>	
	<p align="center"><input type="submit" value="Add"/></p>

</form:form>

</body>
</html>