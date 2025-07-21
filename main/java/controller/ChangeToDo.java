package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ToDo;
import dao.ToDoDAO;

import java.io.IOException;

@WebServlet("/changeToDo")
public class ChangeToDo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String titel = request.getParameter("title");
        String prioritaet = request.getParameter("priority");
        String kategorie = request.getParameter("category");
        String dueDate = request.getParameter("enddate");

        // Aktualisieren in deiner Liste oder DB
        ToDo todo = ToDoDAO.getById(id);
        todo.setTitle(titel);
        todo.setPriority(prioritaet);
        todo.setCategory(kategorie);
        todo.setDueDate(dueDate);
        
        ToDoDAO.update(todo);

        // Weiterleiten zur ToDo-Seite
        request.getRequestDispatcher("ToDoPlanner.jsp").forward(request, response);
    }
}
