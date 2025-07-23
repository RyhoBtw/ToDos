<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login ToDoPlanner</title>
		<link rel="stylesheet" href="Stylesheets/LoginPageStyle.css">
	</head>
	<body>
	<div class="login-container">
        <div id="loginForm">
            <div class="login-header">
                <h1 class="login-title">Willkommen</h1>
                <p class="login-subtitle">Bitte geben Sie Ihren Namen ein</p>
            </div>

            <form id="nameForm" action="sucessful" method="post">
                <div class="form-group">
                    <input 
                        type="text" 
                        name="firstName" 
                        class="form-input" 
                        placeholder="Vorname eingeben"
                        required
                        autocomplete="given-name"
                    >
                    <div class="error-message" id="firstNameError">Bitte geben Sie Ihren Vornamen ein</div>
                </div>

                <div class="form-group">
                    <input 
                        type="text" 
                        name="lastName" 
                        class="form-input" 
                        placeholder="Nachname eingeben"
                        required
                        autocomplete="family-name"
                    >
                    <div class="error-message" id="lastNameError">Bitte geben Sie Ihren Nachnamen ein</div>
                </div>

                <button type="submit" class="login-button" id="submitButton">
                    Anmelden
                </button>
            </form>
        </div>
    </div>

	
	</body>
</html>