package student_registration;

public class Validator {

    public static boolean isValidName(String name) {
        return name.matches("[A-Za-zĀ-ž]{3,}");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidPersonalCode(String code) {
        return code.matches("\\d{6}-\\d{5}");
    }
}
