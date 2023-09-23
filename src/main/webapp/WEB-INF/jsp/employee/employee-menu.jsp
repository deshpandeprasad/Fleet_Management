<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: #333333;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">
<%--     Hello! ${sessionScope.username} |  --%>
    <a style="color:white;text-decoration:none" href="employee-dashboard.htm?username=${sessionScope.username}">Home</a> | 
    <a style="color:white;text-decoration:none" href = "cars.htm?username=${sessionScope.username}">cars</a> |
    <a style="color:white;text-decoration:none" href = "car-reservations.htm?username=${sessionScope.username}">Reservations</a> | 
    <a style="color:white;text-decoration:none" href = "car-returns.htm?username=${sessionScope.username}">Returns</a>
    <a style="color:white;text-decoration:none;float:right" href="logout.htm?username=${sessionScope.username}">Logout</a>
    <label style="float:right">Hi ${sessionScope.username} |</label>
</div>