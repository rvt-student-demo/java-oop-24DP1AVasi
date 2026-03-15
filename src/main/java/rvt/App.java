package rvt;


import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (FileWriter clean = new FileWriter("data/TodoList.csv", false)) {
            clean.write("");
        } catch (IOException e) {
            System.err.println("Error clearing CSV file: " + e.getMessage());
        }
        int taskNum = 1;
        boolean stop = false;
        Scanner scan = new Scanner(System.in);
        try(FileWriter writer = new FileWriter("data/TodoList.csv", true)) {
            writer.append("id,task\n");
            while(stop == false) {
                Scanner scanCsv = new Scanner(new File("data/TodoList.csv"));
                System.out.print("Comand: ");
                String command = scan.nextLine().toLowerCase();

                if (command.equals("add")) {
                    System.out.print("Task: ");
                    writer.append(taskNum + "," + scan.nextLine() + "\n");
                    taskNum ++;
                }

                if (command.equals("list")) {
                    scanCsv.nextLine();
                    while (scanCsv.hasNextLine()) {
                        String parts[] = scanCsv.nextLine().split(",");
                        System.out.println(parts[0] + ": " + parts[1]);
                    }
                }

                if (command.equals("remove")) {
                    System.out.println("Which one you want to remove? ");
                    int num = Integer.valueOf(scan.nextLine());
                    scanCsv.nextLine();
                    for (int i = 0; i < num; i++) {
                        
                    taskNum --;
                    }

                }

                if (command.equals("stop")) {
                    stop = true;
                    scanCsv.close();
                    scan.close();
                    
                }
            }
        } catch (IOException  e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}