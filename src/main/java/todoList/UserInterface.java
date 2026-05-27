package todoList;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UserInterface {

    private Sql list;
    private Scanner scanner;

    public UserInterface(Sql list, Scanner scanner) {
        this.list = list;
        this.scanner = scanner;
    }

    public void start() {
        displayCommands();

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {
                System.out.print("To add: ");
                String task = scanner.nextLine();
                list.addTask(task);
            }

            if (command.equals("list")) {
                list.print();
            }

            if (command.equals("remove")) {
                System.out.print("Which one you want to remove? ");
                int number = Integer.valueOf(scanner.nextLine());
                list.remove(number);
            }

            if (command.equals("table")) {
                new JTables();
            }

            displayCommands();
        }
    }

    private void displayCommands() {
        System.out.println("Available commands:");
        System.out.println("  add    - Add a new task");
        System.out.println("  list   - Display all tasks");
        System.out.println("  remove - Remove a task");
        System.out.println("  table  - Display tasks in a table");
        System.out.println("  stop   - Exit the program");
        System.out.println();
    }
}
