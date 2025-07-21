package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.ToDoDAO;

import java.io.IOException;

@WebServlet("/markTodoDone")
public class MarkDoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int todoId = Integer.parseInt(request.getParameter("todoId"));

        ToDoDAO.markAsDone(todoId);

        // Weiterleiten zur ToDo-Seite
        request.getRequestDispatcher("ToDoPlanner.jsp").forward(request, response);
    }
}
