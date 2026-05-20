package todoList;

// Packages to import
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JTables {
    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    JTables()
    {
        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("JTable");

        // Load data from the project's todo.csv so the table shows current tasks
        String[][] data = loadDataFromCsv("src/main/java/todoList/todo.csv");

        // Column Names
        String[] columnNames = { "id", "task"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }

    private String[][] loadDataFromCsv(String path) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine(); // header line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    rows.add(new String[] { parts[0].trim(), parts[1].trim() });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows.toArray(new String[0][]);
    }

    // Driver  method
    public static void main(String[] args)
    {
        new JTables();
    }
}

