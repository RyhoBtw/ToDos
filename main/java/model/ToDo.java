package model;

import java.io.Serializable;
import java.time.LocalDate;

public class ToDo implements Serializable {
    private int id;
    private String title;
    private String category;
    private String priority;
    private LocalDate dueDate;
    private String status;
    private int userID;
    
    // Constructors
    public ToDo() {
    }
    
    public ToDo(int id, String title, String category, String priority, LocalDate dueDate, String status, int userID) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.userID = userID;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getUserID() {
    	return userID;
    }
    
    public void setUserID(int userID) {
    	this.userID = userID;
    }
    
    @Override
    public String toString() {
        return "Todo [id=" + id + ", title=" + title + ", category=" + category + 
               ", priority=" + priority + ", dueDate=" + dueDate + ", status=" + status + ", userID=" + userID + "]";
    }
}