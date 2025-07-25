package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.ToDo;
import model.User;
import dao.ToDoDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {
	
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	//Aktuellen Benutzer aus der Session holen
    	User user = (User) request.getSession().getAttribute("user");

        String title = request.getParameter("title");
        String priority = request.getParameter("priority");
        if (priority == null) priority = "Low";
        String category = request.getParameter("category");
        if (category == null) category = "Other";
        String status = "ToDo";
        String dueDate = request.getParameter("enddate");
        if(dueDate == null) dueDate = LocalDate.now().toString();
       
        //Prüfen ob ToDo gelöscht werden soll
        if (request.getParameter("delet") != null) {
            ToDoDAO.delete(request.getParameter("delet"));
        } else {
            // Neues ToDo-Objekt erstellen
            ToDo todo = new ToDo();
            todo.setTitle(title);
            todo.setPriority(priority);
            todo.setCategory(category);
            todo.setStatus(status);
            todo.setDueDate(dueDate);
            todo.setUserID(user.getId());
           
            // In der Datenbank speichern
            ToDoDAO.save(todo);
        }

        // Weiterleiten zur ToDo-Seite
        request.getRequestDispatcher("ToDoPlanner.jsp").forward(request, response);
    }
   

}

