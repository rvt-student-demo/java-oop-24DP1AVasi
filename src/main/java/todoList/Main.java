package todoList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize database on startup
        Sql.initializeDatabase();
        
        Sql list = new Sql();
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(list, scanner);
        ui.start();
    }
}
