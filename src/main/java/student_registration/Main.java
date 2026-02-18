package student_registration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Registration reg = new Registration();

        while (true) {
            System.out.println("\nChoose action: register | show | remove | edit | exit");
            String cmd = sc.nextLine().toLowerCase();

            switch (cmd) {
                case "register":
                    reg.registerStudent(sc);
                    break;
                case "show":
                    reg.showStudents();
                    break;
                case "remove":
                    System.out.print("Enter personal code: ");
                    reg.removeStudent(sc.nextLine());
                    break;
                case "edit":
                    System.out.print("Enter personal code: ");
                    reg.editStudent(sc.nextLine(), sc);
                    break;
                case "exit":
                    System.out.println("Goodbye.");
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
