<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="Stylesheet.css">
	<script src="PopUpFenster.js"></script>
	<title>ToDoPlanner</title>
	</head>
	<body>
		<div class="header-row">
		<h1>Welcome, Your Name!</h1>
		<button class="add" type="button" onclick="openPopUp()"><img src="plus.png"/></button>
		</div>
		
		<dialog id="myModal">
	       <h2>My ToDo</h2>
	       <form method="post">
	       	<label for="1">Titel </label> 
	       	<input id="1" name="ToDoName" type="text" required="required" placeholder="MyTask"><br>
	       	<label for="2">Description </label>
	       	<input id="2" name="Description" type="text" required="required" placeholder="Description"><br>
	       	<label for="3">Priority </label>
	       	<input id="3" name="Priority" type="text" required="required" placeholder="Priority"><br>
	       	<label for="4">Category </label>
	       	<input id="4" name="Category" type="text" required="required" placeholder="Category" list="options"/><br>
	       	<datalist id="options">
	       	<option>Homework</option>
	       	<option>Chores</option>
	       	<option>Unknown</option>
	       	</datalist>
	       	<label for="5">Enddate </label>
	       	<input id="5" name="Enddate" type="date" required="required" placeholder="Enddate"><br>
	       	<label for="6">Status </label>
	       	<input id="6" name="Status" type="text" required="required" value="ToDo" disabled="disabled"><br>
	       </form>
	       <button onclick="closePopUp()">Add ToDo</button>
     	</dialog>
     	
     	<div class="kanban-container">
	     	<div class="kanban-column">
	     		<h3>To Do</h3>     	
	     	
	     	</div>
			
			<div class="kanban-column">
				<h3>In Progress</h3>
			</div>
			
			<div class="kanban-column">
				<h3>Done</h3>
			</div>
		</div>
				
			
	</body>
</html>