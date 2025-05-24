<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="Stylesheet.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<script src="PopUpFenster.js"></script>
	<title>ToDoPlanner</title>
	</head>
	<body>
		<header>
			<h1>ToDo Planner</h1>
			<div class="header-buttons">
				<button id="darkModeToggle" onclick="toggleDarkMode()">🌙</button>
				<button class="add-task-btn" type="button" onclick="openPopUp()">＋ New Task</button>
			</div>
		</header>


		<section class="filter-bar">
			<form method="get" action="todo" style="display: flex; gap: 1rem; align-items: center; margin: 0;">
				<!-- Category Filter -->
				<div class="filter">
					<label for="categoryFilter">Category:</label>
					<select id="categoryFilter" name="category" class="pretty-select" onchange="this.form.submit()">
						<option value="" ${param.category == "any" || param.category == null ? "selected" : ""}>All</option>
						<option value="Homework" ${"Homework".equals(param.category) ? "selected" : ""}>Homework</option>
						<option value="Chores" ${"Chores".equals(param.category) ? "selected" : ""}>Chores</option>
						<option value="Other" ${"Other".equals(param.category) ? "selected" : ""}>Other</option>
					</select>
				</div>

				<!-- Priority Filter -->
				<div class="filter">
					<label for="priorityFilter">Priority:</label>
					<select id="priorityFilter" name="priority" class="pretty-select" onchange="this.form.submit()">
						<option value="" ${param.priority == "any" || param.priority == null ? "selected" : ""}>All</option>
						<option value="High" ${"High".equals(param.priority) ? "selected" : ""}>High</option>
						<option value="Medium" ${"Medium".equals(param.priority) ? "selected" : ""}>Medium</option>
						<option value="Low" ${"Low".equals(param.priority) ? "selected" : ""}>Low</option>
					</select>
				</div>
			</form>
		</section>


		<main>
			<section class="task-column">
				<h2>Active Tasks</h2>
				<div id="activeTasks" class="task-list">

				<div class="task-column active-tasks">
					<h2>Active Tasks</h2>
					<ul id="activeTasksList">
					        <ul>
								<c:forEach var="todo" items="${todos}">
									<c:if test="${todo.status == 'ToDo'}">
										<li>
											<div class="task"><strong>${todo.title}</strong> - Priority: ${todo.priority},
												Category: ${todo.category}
												<form action="markTodoDone" method="post">
													<input type="hidden" name="todoId" value="${todo.id}"/>
													<button type="submit">✓</button>
												</form>
											</div></li>
									</c:if>
								</c:forEach>
							</ul>

					</ul>
					<form id="quickAddForm" class="Test" action="todo" method="post">
						<div class="quick-add">
							<input class="quick-add-input" type="text" name="title" placeholder="Quick add task...">
							<button class="add-task-btn" type="submit">＋</button>
						</div>
					</form>
				</div>
				</div>
			</section>

			<section class="task-column">
				<h2>Done Tasks</h2>
				<div id="doneTasks" class="task-list">
					<ul id="doneTasksList">
						<ul>
							<c:forEach var="todo" items="${todos}">
								<c:if test="${todo.status == 'Done'}">
									<li>
										<div class="task"><strong>${todo.title}</strong> - Priority: ${todo.priority},
											Category: ${todo.category}
										</div></li>
								</c:if>
							</c:forEach>
						</ul>

					</ul>
				</div>
			</section>
		</main>
		<dialog id="myModal">
			<h2>New ToDo</h2>
			<form class="add-task-dialog" action="todo" method="post">
				<label for="1">Title</label>
				<input id="1" name="title" type="text" required placeholder="MyTask">

				<label for="3">Priority</label>
				<select id="3" name="priority" required>
					<option value="High">High</option>
					<option value="Medium">Medium</option>
					<option value="Low">Low</option>
				</select><br><br>

				<label for="4">Category</label>
				<input id="4" name="category" type="text" required placeholder="Category" list="options"/><br>
				<datalist id="options">
					<option>Homework</option>
					<option>Chores</option>
					<option>Unknown</option>
				</datalist>

				<label for="5">Enddate</label>
				<input id="5" name="enddate" type="date" required><br>

				<div class="button-group">
					<button type="submit">Add ToDo</button>
					<button type="button" onclick="closePopUp()">Cancel</button>
				</div>
			</form>
		</dialog>
		
		<footer>
			<a class="Impressum" href="Impressum_Datenschutz.html">Impressum & Datenschutzerklärung</a>
			<button class="Help" onclick="window.location.href='Bedienungsanleitung.html'">?</button>
			
		</footer>
		
	</body>
</html>