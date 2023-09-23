<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: #333333;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">   
    <a style="color:white;text-decoration:none" href="customer-dashboard.htm?username=${sessionScope.username}">Home</a> |
   	<a style="color:white;text-decoration:none" href="browse-cars.htm?username=${sessionScope.username}">Browse</a> | 
    <a style="color:white;text-decoration:none" href="my-cars.htm?username=${sessionScope.username}">My Cars</a> | 
    <a style="color:white;text-decoration:none" href="my-reservations.htm?username=${sessionScope.username}">My Reservations</a>
     <a style="color:white;text-decoration:none;float:right" href="logout.htm?username=${sessionScope.username}">Logout</a> 
     <label style="float:right">Hi ${sessionScope.username} |</label>
     
</div>