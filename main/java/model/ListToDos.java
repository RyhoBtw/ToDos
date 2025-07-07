package model;

import java.util.List;

import dao.ToDoDAO;

public class ListToDos{
	User user;
	List <ToDo> todos;
	String priority;
	String category;
	public ListToDos() {
		this.user=null;
		this.todos=null;
		this.priority=null;
		this.category=null;
	}
	
	public void setUser(User user) {
			this.user=user;
		}
	public User getUser() {
		return this.user;
	}
	public void setPrio(String priority) {
		
		this.priority=priority;
	}
	public void setCategory(String kategorie) {
		this.category=kategorie;
	}
	public List <ToDo> getTodos(){
		if (this.user==null ) {
			return null;
		}
		List <String> kategorien=ToDoDAO.getAllCategories();
		if (priority != "" && category != "" && priority != null && category != null) {
            todos = ToDoDAO.getSpecific(priority,category);
        } else if (priority != "" && priority != null) {
            todos = ToDoDAO.getByPrio(priority);
        } else if (category != "" && category != null) {
            todos = ToDoDAO.getByCategory(category);
        } else {
            todos = ToDoDAO.getAll(user.getId());
        }
		
		return todos;
	}
	
}