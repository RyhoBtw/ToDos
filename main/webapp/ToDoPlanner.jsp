<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.User" %>


<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="Stylesheet.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<script src="ToDoPlannerScript.js"></script>
	<title>ToDoPlanner</title>
	</head>
	
	<body>	
	
		<!--  Zugriff auf die ListToDos-Bean -->
		<jsp:useBean id="listToDos" class="model.ListToDos" scope="session" />
		<jsp:setProperty name="listToDos" property="category" param="category" />
		<jsp:setProperty name="listToDos" property="prio" param="priority" />
		<jsp:setProperty name="listToDos" property="user" value="${sessionScope.user}" />
	
		<header>
	           <a class="logo">ToDoPlanner</a>
			<div class="header-buttons">
				<button id="darkModeToggle" onclick="toggleDarkMode()">🌙</button>
				<button class="add-task-btn" type="button" onclick="openPopUp()">＋ New Task</button>
			</div>
		</header>
	
		<section class="content">
	
		<section class="filter-bar">
			<form  style="display: flex; gap: 1rem; align-items: center; margin: 0;" action="todo" method="post">
				<!-- Category Filter -->
				<div class="filter">
					<label for="categoryFilter">Category:</label>
					<select id="categoryFilter" name="category" class="pretty-select" onchange="this.form.submit()">
					<option value="any" ${param.category == 'any' || param.category == null ? 'selected' : ''}>All</option>
	
					
					    <c:forEach var="cat" items="${categories}">
					        <option value="${cat}" ${cat == param.category ? 'selected' : ''}>${cat}</option>
					    </c:forEach>
					</select>
				</div>
	
				<!-- Priority Filter -->
				<div class="filter">
					<label for="priorityFilter">Priority:</label>
					<select id="priorityFilter" name="priority" class="pretty-select" onchange="this.form.submit()">
						<option value="any" ${param.priority == 'any' || param.priority == null ? 'selected' : ''}>All</option>
	
	
	
						<option value="High" ${"High".equals(param.priority) ? "selected" : ""}>High</option>
						<option value="Medium" ${"Medium".equals(param.priority) ? "selected" : ""}>Medium</option>
						<option value="Low" ${"Low".equals(param.priority) ? "selected" : ""}>Low</option>
					</select>
				</div>
			</form>
		</section>
	
		<!-- Spalte in der die ausstehenden ToDos angezeigt werden -->
		<main>
			<section class="task-column">
				<h2>Active Tasks</h2>
				<div id="activeTasks" class="task-list">
	
				<div class="task-column active-tasks">
					<ul id="activeTasksList">
					        <ul>
								<li class="task-header">
									<div class="task-title">Name</div>
									<div class="task-priority">Priority</div>
									<div class="task-category">Category</div>
									<div class="task-category">DueDate</div>
									<div class="task-actions">Actions</div>
								</li>
								<c:forEach var="todo" items="${listToDos.todos}">
									<c:if test="${todo.status == 'ToDo'}">
										<li>
											<div class="task">
												<div class="task-title">
													<strong>${todo.title}</strong>
												</div>
												<div class="task-priority">
														${todo.priority}
												</div>
												<div class="task-category">
														${todo.category}
												</div>
												<div class="task-dueDate">
														${todo.dueDate}
												</div>
																							
												<!-- Container for buttons -->
												<div class="task-actions">
													<!-- Delete Button -->
													<form action="markTodoDone" method="post" style="display: inline;">
														<input type="hidden" name="todoId" value="${todo.id}" />
														<button type="submit" class="done-btn">✓</button>
													</form>
													<!-- Menu Button -->
													
													<input id="todoId" type="hidden" name="todoId" value="${todo.id}" /> 
													<div class="menu-container">
														<button class="menu-button" onclick="toggleMenu(this)">⋮</button>
														<div class="dropdown-menu">
									                        <button class="menu-item edit" onclick="editTodo('${todo.id}','${todo.title}', '${todo.priority}', '${todo.category}', '${todo.dueDate}')"></button>
									                        <form action="todo" method="post" style="display: inline;">
																<input type="hidden" name="delet" value="${todo.id}" />
																<button type="submit" class="menu-item delete"></button>
															</form>
									                    </div>
													</div>
												
												</div>
											</div>
										</li>
									</c:if>
								</c:forEach>
							</ul>
					</ul>
					
					<!-- Quick-Add Funktion -->
					<form id="quickAddForm" class="Test" action="todo" method="post">
						<div class="quick-add">
							<input class="quick-add-input" type="text" name="title" placeholder="Quick add task...">
							<button class="add-task-btn" type="submit">＋</button>
						</div>
					</form>
				</div>
				</div>
			</section>
	
			<!-- Spalte in der die beendeten ToDos angezeigt werden -->
			<section class="task-column">
				<h2>Done Tasks</h2>
				<div id="doneTasks" class="task-list">
					<ul id="doneTasksList">
						<ul>
							<li class="task-header">
								<div class="task-title">Name</div>
								<div class="task-priority">Priority</div>
								<div class="task-category">Category</div>
								<div class="task-actions">Actions</div>
							</li>
							<c:forEach var="todo" items="${listToDos.todos}">
	
								<c:if test="${todo.status == 'Done'}">
								<li>
									<div class="task">
										<div class="task-title">
											<strong>${todo.title}</strong>
										</div>
										<div class="task-priority">
												${todo.priority}
										</div>
										<div class="task-category">
											${todo.category}
										</div>
										<div class="task-actions">
											<!-- Delete Button -->
											<form action="todo" method="post" style="display: inline;">
												<input type="hidden" name="delet" value="${todo.id}" />
												<button type="submit" class="delete-task-btn">🗑️</button>
											</form>
										</div> 
									</div>
								</c:if>
							</c:forEach>
						</ul>
					</ul>
				</div>
			</section>
		</main>
		</section>
		
		<!-- Pop-Up-Fenster zum Erstellen neuer ToDos -->
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
				</select><br>
	
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
					<button type="button" onclick="closePopUp('myModal')">Cancel</button>
				</div>
			</form>
		</dialog>
		
		<!-- Pop-Up-Fenster zum Ändern von ToDos -->	
		<dialog id="myModalChange">
			<h2>Change ToDo</h2>
			<form class="add-task-dialog" action="changeToDo" method="post">
				<input type="hidden" name="id" id="modalId" />
				<label for="1C">Title</label>
				<input id="1C" name="title" type="text" required/>
	
				<label for="3C">Priority</label>
				<select id="3C" name="priority" required>
					<option value="High">High</option>
					<option value="Medium">Medium</option>
					<option value="Low">Low</option>
				</select><br>
	
				<label for="4C">Category</label>
				<input id="4C" name="category" type="text" required list="options"/><br>
				<datalist id="options">
					<option>Homework</option>
					<option>Chores</option>
					<option>Unknown</option>
				</datalist>
	
				<label for="5C">Enddate</label>
				<input id="5C" name="enddate" type="date" required/><br>
	
				<div class="button-group">
					<button type="submit">Add ToDo</button>
					<button type="button" onclick="closePopUp('myModalChange')">Cancel</button>
				</div>
			</form>
		</dialog>
		
		
		<footer>
			<a class="Impressum" href="Impressum_Datenschutz.html">Impressum & Datenschutzerklärung</a>
			<div class="container">
	           	<p>&copy; 2025 ToDoPlanner. Made with ❤️ für produktive Menschen.</p>
			</div>
			<button class="Help" onclick="window.location.href='Bedienungsanleitung.html'">?</button>
		</footer>
				
	</body>
	
</html>