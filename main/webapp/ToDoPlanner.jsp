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
                <div class="filter-group">
                    <label for="category-filter">Category:</label>
                    <select id="category-filter">
                        <option value="all">All Categories</option>
                        <option value="Frontend">Frontend</option>
                        <option value="Backend">Backend</option>
                        <option value="Testing">Testing</option>
                        <option value="DevOps">DevOps</option>
                    </select>
                </div>
                
                <div class="filter-group">
                    <label for="priority-filter">Priority:</label>
                    <select id="priority-filter">
                        <option value="all">All Priorities</option>
                        <option value="High">High</option>
                        <option value="Medium">Medium</option>
                        <option value="Low">Low</option>
                    </select>
           		</div>
			<button class="add" type="button" onclick="openPopUp()"><img src="plus.png"/></button>
		</div>
		
		
		<dialog id="myModal">
	       <h2>My ToDo</h2>
	       <form action="todo" method="post">
	       	<label for="1">Titel </label> 
	       	<input id="1" name="ToDoName" type="text" required="required" placeholder="MyTask"><br>
	       	<label for="3">Priority </label>
	       	<select id="3" required>
               <option value="High">High</option>
               <option value="Medium">Medium</option>
               <option value="Low">Low</option>
            </select><br><br>
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
	       	<div class="button-group">
	       		<button type="submit">Add ToDo</button>
	       		<button onclick="closePopUp()">Cancel</button>
	       	</div>  	
	       </form>
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