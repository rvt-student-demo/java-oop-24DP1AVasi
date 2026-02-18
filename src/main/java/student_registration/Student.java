package student_registration;

public class Student {
    private String name;
    private String surname;
    private String email;
    private String personalCode;
    private String registrationDate;

    public Student(String name, String surname, String email, String personalCode, String registrationDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalCode = personalCode;
        this.registrationDate = registrationDate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String toCSV() {
        return name + "," + surname + "," + email + "," + personalCode + "," + registrationDate;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-25s %-15s %-20s",
                name, surname, email, personalCode, registrationDate);
    }
}
