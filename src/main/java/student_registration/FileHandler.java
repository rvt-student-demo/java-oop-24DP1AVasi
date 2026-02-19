package student_registration;

import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String Path = "src/main/java/student_registration/students.csv";

    public static List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Path))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                list.add(new Student(p[0], p[1], p[2], p[3], p[4]));
            }
        } catch (IOException e) {
            System.out.println("No existing file found, starting fresh.");
        }
        return list;
    }

    public static void saveStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(Path))) {
            for (Student s : students) {
                pw.println(s.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
