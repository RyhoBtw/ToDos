package dao;

import model.ToDo;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDoDAO {

    // Do wird die Verbindung zur Datenbank gmacht. Wenn se net do isch, wird se oigschdugt.
    private static Connection getConnection() throws SQLException, ClassNotFoundException, URISyntaxException {
        String dbPath = "/main/toDos.db";
        java.io.File dbFile = new java.io.File(dbPath);

        // Gucka, ob's Verzeichnis scho gibt – sonscht wirds halt oigschdugt
        java.io.File dbDir = dbFile.getParentFile();
        if (!dbDir.exists()) {
            dbDir.mkdirs(); // Mir Schwoba machat halt selber, wenn nix do isch
        }

        // JDBC-Treiber laade – sonscht geht gar nix
        Class.forName("org.sqlite.JDBC");

        // Verbindig zur SQLite-Datenbank aufbaue
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

        // Falls no kei Tabelle do isch, mach mr glei oine
        initializeDatabase(connection);

        return connection;
    }

    // Do wird die Tabelle aaglegt, wenn's no koi gibt – vorsorglich halt
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
            statement.execute(createTableSQL); // Zack, fertig isch die Tabelle
        }
    }

    // Do wird a neues ToDo gspeichert – also nei in die Datenbank gschrieba
    public static void save(ToDo todo) {
        String sql = "INSERT INTO todos (title, priority, category, status, user_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getPriority());
            stmt.setString(3, todo.getCategory());
            stmt.setString(4, todo.getStatus());
            stmt.setInt(5, todo.getUserID());

            System.out.println("Saved ToDo: " + todo.getTitle());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace(); // Wenn’s kracht, sehma wenigstens warum
        }
    }

    // Holt alle ToDos vom Benutzer – also koi fremde Sachen!
    public static List<ToDo> getAll(int id) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE user_id = ? ORDER BY id DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
        	
            stmt.setInt(1, id); // Nur die ToDos, wo au dem Benutzer ghörat
            ResultSet rs = stmt.executeQuery();
        	
            extractTodos(list, rs); // Helferle zum Liste fülle
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // Holt en ToDo per ID – ganz gezielt halt
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
                todo.setUserID(rs.getInt("user_id"));
                return todo; // Wenn mr ebbes findat, geb mr’s zruck
            }

        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null; // Sonscht halt nix
    }

    // Filtert ToDos na Prio – wenn ma’s halt eilig hat
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

    // Filtert na Kategorie – z.B. "Garten", "Schaffa", "Sonstigs"
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

    // Kombiniert Prio und Kategorie – doppelt hält besser
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
    
    // Holt alle Kategorie – halt für die Übersicht
    public static List<String> getAllCategories(int user){
    	List<String> list = new ArrayList<>();
    	String sql = "SELECT DISTINCT category FROM todos WHERE user_id = ?";
    	
    	try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
    		
    			stmt.setInt(1,  user);
    			ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    String category = rs.getString("category");
                    list.add(category); // Sammlt alles Einzigartige
                }

           } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
               throw new RuntimeException(e);
           }

           return list;
    }

    // Helfer zum ToDos aus'm ResultSet raushole – macht's übersichtlicher
    private static void extractTodos(List<ToDo> list, ResultSet rs) throws SQLException {
        while (rs.next()) {
            ToDo todo = new ToDo();
            todo.setId(rs.getInt("id"));
            todo.setTitle(rs.getString("title"));
            todo.setPriority(rs.getString("priority"));
            todo.setCategory(rs.getString("category"));
            todo.setStatus(rs.getString("status"));
            todo.setUserID(rs.getInt("user_id"));

            list.add(todo);
        }
    }

    // Ändert en ToDo – z.B. wenn ma was vergesse hot
    public static void update(ToDo todo) {
        String sql = "UPDATE todos SET title = ?, priority = ?, category = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getPriority());
            stmt.setString(3, todo.getCategory());
            stmt.setString(4, todo.getStatus());
            stmt.setInt(5, todo.getId());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Schmeißt en ToDo raus – wenn’s erledigt isch oder falsch
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

    // Markiert’s ToDo als „Erledigt“ – wie sich's ghört!
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
