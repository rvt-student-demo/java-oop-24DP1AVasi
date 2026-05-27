package todoList;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Sql {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/todoList/todo.db";
    
    public static void initializeDatabase() {
        try (Connection conn = getConnection();        
             Statement stmt = conn.createStatement()) {
            
            // Create table
            String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY,"
                + "task TEXT NOT NULL) STRICT";
            stmt.executeUpdate(sql);
            
            // Check if table is empty and load from CSV if needed
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM todo")) {
                if (rs.next() && rs.getInt(1) == 0) {
                    insertDataFromCSV(conn, "src/main/java/todoList/todo.csv");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        initializeDatabase();
        System.out.println("Data imported successfully!");
    }
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
    
    public static void insertDataFromCSV(Connection conn, String csvFilePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(csvFilePath));
            
            String insertSql = "INSERT OR REPLACE INTO todo (id, task) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                // Skip header row
                for (int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] parts = line.split(",", 2);
                    
                    if (parts.length >= 2) {
                        int id = Integer.parseInt(parts[0].trim());
                        String task = parts[1].trim();
                        
                        pstmt.setInt(1, id);
                        pstmt.setString(2, task);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }
    
    public static void add(String task) {
        try (Connection conn = getConnection()) {
            String insertSql = "INSERT INTO todo (task) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, task);
                pstmt.executeUpdate();
                System.out.println("Task added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
        exportToCSV();
    }
    
    public static void findAll() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, task FROM todo")) {
            
            System.out.println("\n--- All Tasks ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                System.out.println(id + ", " + task);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }
    
    public static void removeById(int id) {
        try (Connection conn = getConnection()) {
            String deleteSql = "DELETE FROM todo WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Task deleted successfully!");
                } else {
                    System.out.println("No task found with id: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
        exportToCSV();
    }

    public static void exportToCSV() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, task FROM todo ORDER BY id");
             PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/todoList/todo.csv"))) {
            
            writer.println("id,task");
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                writer.println(id + ", " + task);
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error exporting to CSV: " + e.getMessage());
        }
    }

    // Instance methods for DatabaseTodoList compatibility
    public void print() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, task FROM todo ORDER BY id")) {
            
            ArrayList<String> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(rs.getString("task"));
            }
            
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ": " + tasks.get(i));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    public void remove(int number) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id FROM todo ORDER BY id")) {
            
            ArrayList<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
            
            if (number - 1 >= 0 && number - 1 < ids.size()) {
                int idToDelete = ids.get(number - 1);
                removeById(idToDelete);
            }
        } catch (SQLException e) {
            System.out.println("Error removing task: " + e.getMessage());
        }
    }

    public Object[][] getAllTasksAsArray() {
        ArrayList<Object[]> data = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, task FROM todo ORDER BY id")) {
            
            int index = 1;
            while (rs.next()) {
                Object[] row = {index, rs.getString("task")};
                data.add(row);
                index++;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
        return data.toArray(new Object[0][]);
    }

    // Instance method wrapper for add
    public void addTask(String task) {
        add(task);
    }
}
