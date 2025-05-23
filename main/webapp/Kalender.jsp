<%--
  Created by IntelliJ IDEA.
  User: rhyo
  Date: 5/19/25
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Kalenderansicht – Todo Planner</title>
    <link rel="stylesheet" href="Stylesheet.css"/>
</head>
<body>
<header><h1>Kalenderansicht</h1>
    <div class="header-buttons">
        <button id="darkModeToggle" onclick="toggleDarkMode()">🌙 Dark Mode</button>
        <a href="ToDoPlanner.jsp" class="add-task-btn" style="text-decoration: none;">Zurück</a></div>
</header>
<main class="calendar-main">
    <div class="calendar-placeholder"><h2>Kalender wird bald angezeigt</h2>
        <p>Hier entsteht die Kalenderansicht für deine Aufgaben.</p></div>
</main>
<script> function toggleDarkMode() {
    document.body.classList.toggle("dark-mode");
    if (document.body.classList.contains("dark-mode")) {
        localStorage.setItem("theme", "dark");
    } else {
        localStorage.setItem("theme", "light");
    }
}

window.addEventListener("DOMContentLoaded", () => {
    if (localStorage.getItem("theme") === "dark") {
        document.body.classList.add("dark-mode");
    }
}); </script>
</body>
</html>
