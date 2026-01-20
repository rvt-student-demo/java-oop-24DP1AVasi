package rvt;


import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int taskNum = 1;
        boolean stop = false;
        Scanner scan = new Scanner(System.in);
        try(FileWriter writer = new FileWriter("data/TodoList.csv", true)) {
            writer.append("id,task\n");
            while(stop == false) {
                System.out.println("Comand: ");
                String command = scan.nextLine();

                if (command == "add") {
                    System.out.print("Task: ");
                    writer.append(taskNum +","+ scan.nextLine());
                    taskNum ++;
                    System.out.println();
                }

                if (command == "list") {

                }

                if (command == "completed") {

                }

                if (command == "stop") {
                    stop = true;
                }
            }

        } catch (IOException  e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}