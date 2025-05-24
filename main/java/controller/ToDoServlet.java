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

        List<ToDo> todos;

        if (request.getParameter("priority") != "" && request.getParameter("category") != "" && request.getParameter("priority") != null && request.getParameter("category") != null) {
            todos = ToDoDAO.getSpecific(request.getParameter("priority"), request.getParameter("category"));
        } else if (request.getParameter("priority") != "" && request.getParameter("priority") != null) {
            todos = ToDoDAO.getByPrio(request.getParameter("priority"));
        } else if (request.getParameter("category") != "" && request.getParameter("category") != null) {
            todos = ToDoDAO.getByCategory(request.getParameter("category"));
        } else {
            todos = ToDoDAO.getAll();
        }
        request.setAttribute("todos", todos);

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
        if (request.getParameter("delet") != null) {
            ToDoDAO.delete(request.getParameter("delet"));
        } else {
            // Neues ToDo-Objekt erstellen
            ToDo todo = new ToDo();
            todo.setTitle(title);
            todo.setPriority(priority);
            todo.setCategory(category);
            todo.setStatus(status);

            // In der Datenbank speichern
            ToDoDAO.save(todo);
        }

        // Weiterleiten auf doGet (Liste aktualisieren)
        response.sendRedirect("todo");
    }
}

