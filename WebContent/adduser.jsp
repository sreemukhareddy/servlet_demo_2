<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add_User</title>
</head>
<body>
<!-- <form action="addServlet" method="post"> -->
<!-- <form action="servletconfigwithwebdotxml" method="post"> -->
<form action="servletcontextwithwebdotxml" method="post">
	First Name:<input type="text" name="firstName" /> <br>
	Last Name:<input type="text" name="lastName" /> <br>
	Email:<input type="text" name="email" /> <br>
	Password:<input type="password" name="password" /> <br>
	<input type="submit" name="submit" value="submit" />

</form>
</body>
</html>