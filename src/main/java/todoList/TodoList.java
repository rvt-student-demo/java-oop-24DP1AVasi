package todoList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TodoList {

    private ArrayList<String> tasks;
    private String filePath;

    public TodoList() {
        this("src/main/java/todoList/todo.csv");
    }

    public TodoList(String filePath) {
        this.tasks = new ArrayList<>();
        this.filePath = filePath;
        loadFromFile();
    }

    public void add(String task) {
        this.tasks.add(task);
        saveToFile();
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    public void remove(int number) {
        if (number - 1 >= 0 && number - 1 < tasks.size()) {
            tasks.remove(number - 1);
            saveToFile();
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    tasks.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            // If file doesn't exist or cannot be read, start with empty list
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("id,task");
            for (int i = 0; i < tasks.size(); i++) {
                writer.println(i + ", " + tasks.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
