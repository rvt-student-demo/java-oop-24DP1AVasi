package rvt;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try(Scanner scan = new Scanner(new File("data/data.csv"))) {
            scan.nextLine(); // skip header
            while(scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
