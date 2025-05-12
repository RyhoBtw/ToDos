<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="Stylesheet.css">
	<link rel="popup" href="ToDoCards.html">
	 <script src="PopUpFenster.js"></script>
	 <title>ToDoPlanner</title>
	</head>
	<body>
		<h1>Willkommen, Dein Name!</h1>
		<button class="add" type="button" onclick="openPopUp()">New ToDo</button>
		
		<dialog id="myModal">
	       <p>My ToDo</p>
	       <form method="get">
	       	<input name="ToDoName" type="text" required="required">
	       </form>
	       <button onclick="closePopUp()">Schließen</button>
     	</dialog>
		
			
	</body>
</html>