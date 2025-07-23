package dao;

import model.ToDo;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDoDAO {

    // Verbindung zur Datenbank herstellen
    private static Connection getConnection() throws SQLException, ClassNotFoundException, URISyntaxException {
        String dbPath = "/main/toDos.db";
        java.io.File dbFile = new java.io.File(dbPath);

     
        java.io.File dbDir = dbFile.getParentFile();
        if (!dbDir.exists()) {
            dbDir.mkdirs(); 
        }

        
        Class.forName("org.sqlite.JDBC");

        // Verbindung zur SQLite-Datenbank aufbauen
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

        // Falls noch keine Tabelle vorhanden, neue erstellen
        initializeDatabase(connection);

        return connection;
    }

    // Initialisierung der Tabelle
    private static void initializeDatabase(Connection connection) throws SQLException {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS todos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            description TEXT,
            priority TEXT,
            category TEXT,
            due_date TEXT,
            status TEXT,
            user_id INTEGER
        );
        """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL); 
        }
    }

    // Neues ToDo in die Datenbak einfügen
    public static void save(ToDo todo) {
        String sql = "INSERT INTO todos (title, priority, category, status, due_date, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getPriority());
            stmt.setString(3, todo.getCategory());
            stmt.setString(4, todo.getStatus());
            stmt.setString(5, todo.getDueDate());
            stmt.setInt(6, todo.getUserID());

            System.out.println("Saved ToDo: " + todo.getTitle());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace(); 
        }
    }

    // Holt alle ToDos von einem bestimmten Benutzer
    public static List<ToDo> getAll(int id) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE user_id = ? ORDER BY id DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
        	
            stmt.setInt(1, id); 
            ResultSet rs = stmt.executeQuery();
        	
            extractTodos(list, rs); 
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // Holt ein ToDo nach ID
    public static ToDo getById(int id) {
        String sql = "SELECT * FROM todos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ToDo todo = new ToDo();
                todo.setId(rs.getInt("id"));
                todo.setTitle(rs.getString("title"));
                todo.setPriority(rs.getString("priority"));
                todo.setCategory(rs.getString("category"));
                todo.setStatus(rs.getString("status"));
                todo.setDueDate(rs.getString("due_date"));
                todo.setUserID(rs.getInt("user_id"));
                return todo; 
            }

        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null; 
    }

    // Holt alle To Dos einer bestimmten Priorität eines Benutzers
    public static List<ToDo> getByPrio(String priority, int user) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE priority = ? AND user_id =?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, priority);
            stmt.setInt(2, user);
            ResultSet rs = stmt.executeQuery();

            extractTodos(list, rs);
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Holt alle To Dos einer bestimmten Kategorie eines Benutzers
    public static List<ToDo> getByCategory(String category, int user) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE category = ? AND user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            stmt.setInt(2, user);
            ResultSet rs = stmt.executeQuery();

            extractTodos(list, rs);
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Holt alle To Dos einer bestimmten Priorität und Kategorie eines Benutzers
    public static List<ToDo> getSpecific(String priority, String category, int user) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE priority = ? AND category = ? AND user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, priority);
            stmt.setString(2, category);
            stmt.setInt(3, user);
            ResultSet rs = stmt.executeQuery();

            extractTodos(list, rs);
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    // Holt alle Kategorien eines Benutzers
    public static List<String> getAllCategories(int user){
    	List<String> list = new ArrayList<>();
    	String sql = "SELECT DISTINCT category FROM todos WHERE user_id = ?";
    	
    	try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
    		
    			stmt.setInt(1,  user);
    			ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    String category = rs.getString("category");
                    list.add(category);
                }

           } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
               throw new RuntimeException(e);
           }

           return list;
    }

    // Initialisiert ToDo-Objekte aus dem ResultSet
    private static void extractTodos(List<ToDo> list, ResultSet rs) throws SQLException {
        while (rs.next()) {
            ToDo todo = new ToDo();
            todo.setId(rs.getInt("id"));
            todo.setTitle(rs.getString("title"));
            todo.setPriority(rs.getString("priority"));
            todo.setCategory(rs.getString("category"));
            todo.setStatus(rs.getString("status"));
            todo.setDueDate(rs.getString("due_date"));
            todo.setUserID(rs.getInt("user_id"));

            list.add(todo);
        }
    }

    // Ändert ein ToDo in der Datenbank
    public static void update(ToDo todo) {
        String sql = "UPDATE todos SET title = ?, priority = ?, category = ?, due_Date = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getPriority());
            stmt.setString(3, todo.getCategory());
            stmt.setString(4, todo.getDueDate());
            stmt.setString(5, todo.getStatus());
            stmt.setInt(6, todo.getId());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Löscht ein ToDo aus der Datenbank
    public static void delete(String id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Setzt den Status eines ToDos auf "Done"
    public static void markAsDone(int id) {
        String sql = "UPDATE todos SET status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Done");
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
