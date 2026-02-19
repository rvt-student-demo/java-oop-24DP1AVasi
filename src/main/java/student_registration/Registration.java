package student_registration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Registration {

    private List<Student> students;

    public Registration() {
        students = FileHandler.loadStudents();
    }

    public void registerStudent(Scanner sc) {
        String name;
        String surname;
        String email;
        String code;
        String date;

        while(true) {
            System.out.print("Enter name: ");
            name = sc.nextLine();
            if (Validator.isValidName(name)) {
                break;
            }
            System.out.println("Invalid name.");
        }

        while(true) {
            System.out.print("Enter surname: ");
            surname = sc.nextLine();
            if (Validator.isValidName(surname)) {
                break;
            }
            System.out.println("Invalid surname.");
        }

        while(true) {
            System.out.print("Enter email: ");
            email = sc.nextLine();
            if (Validator.isValidEmail(email) && !emailExists(email)) {
                break;
            }
            System.out.println("Invalid or taken email.");
        }

        while(true) {
            System.out.print("Enter personal code (######-#####): ");
            code = sc.nextLine();
            if (Validator.isValidPersonalCode(code) && !personalCodeExists(code)) {
                break;
            }
            System.out.println("Invalid or existing personal code.");
        }

        date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Student s = new Student(name, surname, email, code, date);
        students.add(s);
        FileHandler.saveStudents(students);

        System.out.println("Student registered successfully.");
    }

    public void showStudents() {
        System.out.printf("%-15s %-15s %-25s %-15s %-20s%n",
                "Name", "Surname", "Email", "Code", "Registered");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void removeStudent(String code) {
        students.removeIf(s -> s.getPersonalCode().equals(code));
        FileHandler.saveStudents(students);
    }

    public void editStudent(String code, Scanner sc) {
        for (Student s : students) {
            if (s.getPersonalCode().equals(code)) {
                System.out.print("New email: ");
                String newEmail = sc.nextLine();
                if (Validator.isValidEmail(newEmail)) {
                    s.setEmail(newEmail);
                }
                FileHandler.saveStudents(students);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private boolean emailExists(String email) {
        return students.stream().anyMatch(s -> s.getEmail().equals(email));
    }

    private boolean personalCodeExists(String code) {
        return students.stream().anyMatch(s -> s.getPersonalCode().equals(code));
    }
}
