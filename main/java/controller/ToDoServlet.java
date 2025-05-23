package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.ToDo;
import dao.ToDoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Alle ToDos aus der Datenbank holen
        List<ToDo> todos = ToDoDAO.getAll();

        // In den Request setzen (für die JSP)
        request.setAttribute("todos", todos);

        // Weiterleiten an die JSP
        request.getRequestDispatcher("ToDoPlanner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String priority = request.getParameter("priority");
        if (priority == null) priority = "Low";
        String category = request.getParameter("category");
        if (category == null) category = "Other";
        String status = "ToDo";

        // Neues ToDo-Objekt erstellen
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setPriority(priority);
        todo.setCategory(category);
        todo.setStatus(status);

        // In der Datenbank speichern
        ToDoDAO.save(todo);
        
        System.out.println("Hallllllooooo");

        // Weiterleiten auf doGet (Liste aktualisieren)
        response.sendRedirect("todo");
    }
}

