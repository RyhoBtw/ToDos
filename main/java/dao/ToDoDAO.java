package dao;

import model.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDAO {

    // Verbindung zur Datenbank aufbauen
    private static Connection getConnection() throws SQLException {
        // Passen Sie die Zugangsdaten und URL an Ihre Datenbank an
        String url = "jdbc:mysql://localhost:3306/tododb";
        String user = "root";
        String password = "passwort";
        return DriverManager.getConnection(url, user, password);
    }

    // Neues ToDo speichern
    public static void save(ToDo todo) {
        String sql = "INSERT INTO todos (title, priority, category, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(3, todo.getPriority());
            stmt.setString(4, todo.getCategory());
            stmt.setString(5, todo.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Alle ToDos abrufen
    public static List<ToDo> getAll() {
        List<ToDo> list = new ArrayList<>();
        String sql = "SELECT * FROM todos ORDER BY id DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ToDo todo = new ToDo();
                todo.setId(rs.getInt("id"));
                todo.setTitle(rs.getString("title"));
                todo.setPriority(rs.getString("priority"));
                todo.setCategory(rs.getString("category"));
                todo.setStatus(rs.getString("status"));

                list.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        }
    }

    // ToDo löschen
    public static void delete(int id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
