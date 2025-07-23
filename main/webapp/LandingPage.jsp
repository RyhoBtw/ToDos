<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login ToDoPlanner</title>
		<link rel="stylesheet" href="Stylesheets/LandingPage.css">
	</head>
	<body>
	
	<div class="bg-animation">
        <div class="floating-shape"></div>
        <div class="floating-shape"></div>
        <div class="floating-shape"></div>
    </div>

    <header>
        <nav>
            <img src="LOGO.png">
        </nav>
    </header>

    <section class="hero">
        <div class="container">
            <div class="hero-content">
                <h1>Organisiere dein Leben</h1>
                <p>Mit ToDoPlanner behältst du den Überblick über alle deine Aufgaben. Elegant, intuitiv und kraftvoll - so wie Produktivität sein sollte.</p>
                <p>Erstelle einfach neue ToDo-Cards und organisiere sie nach ihrer Kategorie oder Priorität - so weißt du immer genau was als Erstes erledigt werden muss.</p>
                <a href="Login.jsp" class="cta-button">Jetzt starten</a>
            </div>
        </div>
    </section>

    <section class="features" id="features">
        <div class="container">
            <h2>Warum ToDoPlanner?</h2>
            <div class="features-grid">
                <div class="feature-card">
                    <span class="feature-icon">🎯</span>
                    <h3>Fokussiert bleiben</h3>
                    <p>Priorisiere deine Aufgaben intelligent nach Wichtigkeit und Kategorie - Konzentriere dich nur auf das Wesentliche. Schluss mit unübersichtlichen To-Do-Listen.</p>
                </div>
                <div class="feature-card">
                    <span class="feature-icon">⚡</span>
                    <h3>Blitzschnell</h3>
                    <p>Erstelle und verwalte Aufgaben in Sekundenschnelle. Keine komplizierte Navigation - einfach nur pure Effizienz.</p>
                </div>
                <div class="feature-card">
                    <span class="feature-icon">📅</span>
                    <h3>Übersichtlich</h3>
                    <p>Plane deine Aufgaben zeitlich perfekt. Mit der integrierten Kalenderansicht behältst du Termine und Deadlines immer im Blick.</p>
                </div>
            </div>
        </div>
    </section>



    <footer>
        <div class="container">
            <p>&copy; 2025 ToDoPlanner. Made with ❤️ für produktive Menschen.</p>
        </div>
    </footer>
		
	</body>
</html>