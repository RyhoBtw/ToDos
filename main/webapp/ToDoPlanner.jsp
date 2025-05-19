<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="Stylesheet.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<script src="PopUpFenster.js"></script>
	<title>ToDoPlanner</title>
	</head>
	<body>
		<header>
			<h1>Todo Planner</h1>
			<button class="add-task-btn" type="button" onclick="openPopUp()">+ New Task</button>
		</header>

		<section class="filter-bar">
			<div class="filter">
				<label for="categoryFilter">Category:</label>
				<select id="categoryFilter" class="pretty-select" onchange="filterTasks()">
					<option value="All">All</option>
					<option value="Homework">Homework</option>
					<option value="Chores">Chores</option>
					<option value="Unknown">Unknown</option>
				</select>
			</div>
			<div class="filter">
				<label for="priorityFilter">Priority:</label>
				<select id="priorityFilter" class="pretty-select" onchange="filterTasks()">
					<option value="All">All</option>
					<option value="High">High</option>
					<option value="Medium">Medium</option>
					<option value="Low">Low</option>
				</select>
			</div>
		</section>

		</section>

		<main>
			<section class="task-column">
				<h2>Active Tasks</h2>
				<div id="activeTasks" class="task-list">

				<div class="task-column active-tasks">
					<h2>Active Tasks</h2>
					<ul id="activeTasksList">
<%--					<div class="task">Walk the dog <button onclick="markDone(this)">?</button></div>--%>
					</ul>
					<div class="quick-add">
						<input
								type="text"
								id="quickAddInput"
								placeholder="Quick add task..."
						/>
						<button onclick="quickAddTask()">?</button>
					</div>
				</div>
				</div>
			</section>

			<section class="task-column">
				<h2>Done Tasks</h2>
				<div id="doneTasks" class="task-list">
<%--					<div class="task done">Read emails</div>--%>
				</div>
			</section>
		</main>
		<dialog id="myModal">
			<h2>New ToDo</h2>
			<form action="todo" method="post">
				<label for="1">Title</label>
				<input id="1" name="ToDoName" type="text" required placeholder="MyTask"><br>

				<label for="3">Priority</label>
				<select id="3" name="Priority" required>
					<option value="High">High</option>
					<option value="Medium">Medium</option>
					<option value="Low">Low</option>
				</select><br><br>

				<label for="4">Category</label>
				<input id="4" name="Category" type="text" required placeholder="Category" list="options"/><br>
				<datalist id="options">
					<option>Homework</option>
					<option>Chores</option>
					<option>Unknown</option>
				</datalist>

				<label for="5">Enddate</label>
				<input id="5" name="Enddate" type="date" required><br>

				<label for="6">Status</label>
				<input id="6" name="Status" type="text" value="ToDo" disabled><br>

				<div class="button-group">
					<button type="submit">Add ToDo</button>
					<button type="button" onclick="closePopUp()">Cancel</button>
				</div>
			</form>
		</dialog>
	</body>
</html>