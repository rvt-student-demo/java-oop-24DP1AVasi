package todoList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTables {
    private JFrame frame;
    private JTable table;
    private Sql sqlDatabase;

    public JTables() {
        this(new Sql());
    }

    public JTables(Sql sqlDatabase) {
        this.sqlDatabase = sqlDatabase;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Todo List Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Id", "Task"};
        Object[][] data = sqlDatabase.getAllTasksAsArray();

        table = new JTable(data, columnNames);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Sql.initializeDatabase();
        new JTables();
    }
}

