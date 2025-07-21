package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ListToDos;
import model.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ToDoDAO;

@WebServlet("/sucessful")
public class LoginServlet extends HttpServlet {
	
	private int i = 0;
	private List<User> users = new ArrayList<>();
	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Suche nach bestehendem Benutzer
        User user = null;
        for (User u : users) {
            if (u.getVorname().equals(firstName) && u.getNachname().equals(lastName)) {
                user = u;
                break;
            }
        }

        // Wenn der User nicht gefunden wurde, erstelle einen neuen
        if (user == null) {
            user = new User(i++, firstName, lastName);
            users.add(user);
        }

        // User in der Session speichern
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Liste von Todos und ListToDos bean in der Session speichern
        ListToDos listToDos = new ListToDos();
        listToDos.setUser(user);
        session.setAttribute("listToDos", listToDos);
        List<String> categories = ToDoDAO.getAllCategories(user.getId());
        request.getSession().setAttribute("categories", categories);


        // Weiterleiten zur ToDo-Seite
        request.getRequestDispatcher("ToDoPlanner.jsp").forward(request, response);
    }
	
	public List<User> getUsers(){
		return users;
	}
}
