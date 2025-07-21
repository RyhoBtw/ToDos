package model;

import java.util.List;

import dao.ToDoDAO;

public class ListToDos{
	private User user;
	private List <ToDo> todos;
	private List <String> categories;
	private String priority;
	private String category;
	private String property;
	public ListToDos() {
		this.user=null;
		this.todos=null;
		this.priority=null;
		this.category=null;
		this.categories=null;
	}
	
	public void setUser(User user) {
			this.user=user;
		}
	public User getUser() {
		return this.user;
	}
	
	public void setCategory(String kategorie) {
		this.category=kategorie;
	}
		
	public List<ToDo> getTodos() {
	    if (this.user == null) return null;

	    boolean hasPriority = priority != null && !priority.equals("any");
	    boolean hasCategory = category != null && !category.equals("any");


	    if (hasPriority && hasCategory) {
	        todos = ToDoDAO.getSpecific(priority, category, user.getId());
	    } else if (hasPriority) {
	        todos = ToDoDAO.getByPrio(priority, user.getId());
	    } else if (hasCategory) {
	        todos = ToDoDAO.getByCategory(category, user.getId());
	    } else {
	        todos = ToDoDAO.getAll(user.getId());
	    }

	    return todos;
	}

	
	public void setPrio(String priority) {
	    this.priority = priority;
	}

}