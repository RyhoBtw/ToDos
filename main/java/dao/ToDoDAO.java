package dao;

import model.ToDo;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDoDAO {

    private static Connection getConnection() throws SQLException, ClassNotFoundException, URISyntaxException {
        String dbPath = "database/toDos.db";
        java.io.File dbFile = new java.io.File(dbPath);

        java.io.File dbDir = dbFile.getParentFile();
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

        initializeDatabase(connection);

        return connection;
    }

    private static void initializeDatabase(Connection connection) throws SQLException {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS todos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            description TEXT,
            priority TEXT,
            category TEXT,
            due_date TEXT,
            status TEXT
        );
        """;

        try (Statement statement = connection.createStatement()) {
            // Execute the creation query
            statement.execute(createTableSQL);
        }
    }


    public static void save(ToDo todo) {
        String sql = "INSERT INTO todos (title, priority, category, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getPriority());
            stmt.setString(3, todo.getCategory());
            stmt.setString(4, todo.getStatus());

            System.out.println("Saved ToDo: " + todo.getTitle());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Alle ToDos abrufen
    public static List<ToDo> getAll() {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos ORDER BY id DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            extractTodos(list, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // ToDo nach ID abrufen
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
                return todo;
            }

        } catch (SQLException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<ToDo> getByPrio(String priority) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE priority = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, priority);
            ResultSet rs = stmt.executeQuery();

            extractTodos(list, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<ToDo> getByCategory(String category) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE category = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            extractTodos(list, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<ToDo> getSpecific(String priority, String category) {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE priority = ? AND category = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, priority);
            stmt.setString(2, category);
            ResultSet rs = stmt.executeQuery();

            extractTodos(list, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static void extractTodos(List<ToDo> list, ResultSet rs) throws SQLException {
        while (rs.next()) {
            ToDo todo = new ToDo();
            todo.setId(rs.getInt("id"));
            todo.setTitle(rs.getString("title"));
            todo.setPriority(rs.getString("priority"));
            todo.setCategory(rs.getString("category"));
            todo.setStatus(rs.getString("status"));

            list.add(todo);
        }
    }

    // ToDo aktualisieren
    public static void update(ToDo todo) {
        String sql = "UPDATE todos SET title = ?, priority = ?, category = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(3, todo.getPriority());
            stmt.setString(4, todo.getCategory());
            stmt.setString(5, todo.getStatus());
            stmt.setInt(6, todo.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // ToDo löschen
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

    public static void markAsDone(int id) {
        String sql = "UPDATE todos SET status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Done");
            stmt.setString(2, String.valueOf(id));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
