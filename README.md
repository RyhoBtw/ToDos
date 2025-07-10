# Technische Dokumentation – ToDos

## 1. Projektüberblick

Das Projekt **ToDos** ist eine webbasierte ToDo-Listen-Anwendung, entwickelt in Java mit dem Jakarta Servlet Framework. Die Anwendung ermöglicht Benutzern das Anlegen, Anzeigen und Verwalten von Aufgaben. Die Daten werden in einer SQLite-Datenbank gespeichert.

## 2. Verwendete Technologien

- **Programmiersprache:** Java
- **Build-Tool:** Maven
- **Web-Framework:** Jakarta Servlet API
- **Frontend:** JSP, HTML, CSS, JavaScript
- **Datenbank:** SQLite (JDBC)
- **Bibliotheken:** JSTL, Gson

## 3. Projektstruktur

- `src/main/java/controller/`: Enthält Servlets für Login, ToDo-Verwaltung und Statusänderungen.
- `src/main/java/dao/`: Datenzugriffsschicht (Data Access Object) für ToDos.
- `src/main/java/model/`: Datenmodelle für User und ToDos.
- `src/main/webapp/`: Statische und dynamische Webressourcen (JSP, HTML, CSS, JS).
- `src/main/webapp/WEB-INF/lib/`: Externe Bibliotheken (JARs).
- `src/main/webapp/WEB-INF/`: Konfigurationsdateien.

## 4. Hauptkomponenten

### 4.1 Servlets

- **LoginServlet:** Verarbeitet Login-Anfragen, verwaltet User-Session.
- **ToDoServlet:** Zeigt ToDos an, verarbeitet neue Einträge.
- **MarkDoneServlet:** Markiert Aufgaben als erledigt.

### 4.2 Datenmodelle

- **User:** Repräsentiert einen Benutzer (ID, Vorname, Nachname).
- **ToDo:** Repräsentiert eine Aufgabe (ID, Beschreibung, Status, User).
- **ListToDos:** Hilfsklasse für ToDo-Listen.

### 4.3 Datenzugriff

- **ToDoDAO:** Kapselt alle Datenbankzugriffe (CRUD-Operationen für ToDos).

## 5. Datenbank

- SQLite-Datenbank (`toDos.db`) im Projektverzeichnis.
- Tabellen: `users`, `todos`.

## 6. Build & Deployment

- **Build:** `./mvnw clean package`
- **Deployment:** Das erzeugte WAR-Archiv kann in einen Servlet-Container (z\.B\. Tomcat) deployt werden.
- **Landing Page:** `https:localhost:8080/Login.jsp`

## 7. Sicherheit

- Session-Management über `HttpSession`.
- Keine Passwörter, nur Name-basierter Login (Demo-Zwecke).

## 8. Erweiterungsmöglichkeiten

- Benutzer-Authentifizierung mit Passwort.
- REST-API für externe Clients.
- Responsive Design für mobile Geräte.
