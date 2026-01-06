package rvt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PasutijumuVesture {
    public static void main(String[] args) {
        double sum = 0;
        try(Scanner scan = new Scanner(new File("data/data.csv"))) {
                scan.nextLine(); // skip header
                while(scan.hasNextLine()) {
                    String row = scan.nextLine();
                    String[] parts = row.split(",");

                    int id = Integer.valueOf(parts[0]);
                    String name = parts[1];
                    String product = parts[2];
                    int quantity = Integer.valueOf(parts[3]);
                    double price = Double.valueOf(parts[4]);
                    sum += quantity * price;
                    System.out.println("Pasutijums #"+id+": "+name+" pasutija "+quantity+" x "+product+" ("+price+" EUR) -> kopa: "+(quantity*price)+" EUR");
                }
                System.out.println("\nViss kopā: "+sum+" EUR");
            }
            catch(FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
        }
    }
}