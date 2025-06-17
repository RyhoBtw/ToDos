package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import model.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/sucessful")
public class LoginServlet extends HttpServlet {
	
	private int i = 0;
	private List<User> users = new ArrayList<>();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");

	    // Suche nach bestehendem Benutzer
	    boolean match = false;
	    for (User u : users) {
	        if (u.getVorname().equals(firstName) && u.getNachname().equals(lastName)) {
	            user = u;
	            match = true;
	            break;
	        }
	    }

	    // Wenn nicht gefunden → neu erstellen und hinzufügen
	    if (match == false) {
	        user = new User(i++, firstName, lastName);
	        users.add(user);
	    }
	    
	  	    
	    request.getSession().setAttribute("user", user);
		
		response.sendRedirect("todo");
	}
	
	public List<User> getUsers(){
		return users;
	}
}
