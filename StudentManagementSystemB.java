import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Abstraction: Abstract base class representing generic human data
abstract class Person {
    private String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Encapsulation: Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public abstract void displayInfo();
}

// Inheritance: Student inherits from Person
class Student extends Person {
    private String course;

    public Student(String studentId, String studentName, String email, String course) {
        super(studentId, studentName, email);
        this.course = course;
    }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    // Polymorphism: Method Overriding
    @Override
    public void displayInfo() {
        System.out.println("ID: " + getId() + " | Name: " + getName() + " | Email: " + getEmail() + " | Course: " + course);
    }
}

// Main System Class handling full CRUD operations
public class StudentManagementSystemB {
    private static final Scanner input = new Scanner(System.in);
    private static final List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=== Advanced Student Management System ===");

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register New Student (Create)");
            System.out.println("2. Display All Students (Read)");
            System.out.println("3. Update Student Record (Update)");
            System.out.println("4. Delete Student Record (Delete)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine(); // Clear buffer

                switch (choice) {
                    case 1 -> registerStudent();
                    case 2 -> displayAllStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> {
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                    }
                    default -> System.out.println("Invalid selection. Please enter 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                input.nextLine(); // Clear bad input
            }
        }
        input.close();
    }

    // 1. CREATE: Register a new student
    private static void registerStudent() {
        System.out.println("\n--- Register Student ---");
        System.out.print("Enter Student ID: ");
        String id = input.nextLine().trim();

        // Check for duplicate IDs
        if (findStudentById(id) != null) {
            System.out.println("[Error] A student with ID " + id + " already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = input.nextLine().trim();

        // --- ENFORCED COURSE SELECTION LOOP (MINIMAL CHANGE) ---
        String course = "";
        while (true) {
            System.out.println("\nSelect Course Allocation:");
            System.out.println("1. Java Programming");
            System.out.println("2. Database Systems");
            System.out.print("Enter Choice (1-2): ");
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                course = "Java Programming";
                break;
            } else if (choice.equals("2")) {
                course = "Database Systems";
                break;
            } else {
                System.out.println("[Error] Invalid option. Please enter exactly 1 or 2.");
            }
        }
        // --------------------------------------------------------

        studentList.add(new Student(id, name, email, course));
        System.out.println("[Success] Student registered successfully.");
    }

    // 2. READ: Display summary of all records
    private static void displayAllStudents() {
        System.out.println("\n--- Student Registry Summary ---");
        if (studentList.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Student student : studentList) {
            student.displayInfo();
        }
        System.out.println("Total Records: " + studentList.size());
    }

    // 3. UPDATE: Modify an existing record
    private static void updateStudent() {
        System.out.println("\n--- Update Student Record ---");
        System.out.print("Enter the ID of the student to update: ");
        String id = input.nextLine().trim();

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("[Error] Student record not found.");
            return;
        }

        System.out.print("Enter New Name (or press Enter to keep '" + student.getName() + "'): ");
        String newName = input.nextLine().trim();
        if (!newName.isEmpty()) student.setName(newName);

        System.out.print("Enter New Email (or press Enter to keep '" + student.getEmail() + "'): ");
        String newEmail = input.nextLine().trim();
        if (!newEmail.isEmpty()) student.setEmail(newEmail);

        // --- OPTIONAL COURSE SELECTION LOOP (MINIMAL CHANGE) ---
        while (true) {
            System.out.println("\nSelect Course Allocation:");
            System.out.println("1. Java Programming");
            System.out.println("2. Database Systems");
            System.out.print("Enter Choice (1-2) or press Enter to keep '" + student.getCourse() + "'): ");
            String choice = input.nextLine().trim();

            if (choice.isEmpty()) {
                break; // Keep the existing student course allocation intact
            } else if (choice.equals("1")) {
                student.setCourse("Java Programming");
                break;
            } else if (choice.equals("2")) {
                student.setCourse("Database Systems");
                break;
            } else {
                System.out.println("[Error] Invalid option. Please enter 1, 2, or press Enter.");
            }
        }
        // -------------------------------------------------------

        System.out.println("[Success] Student record updated successfully.");
    }

    // 4. DELETE: Remove a record from the database
    private static void deleteStudent() {
        System.out.println("\n--- Delete Student Record ---");
        System.out.print("Enter the ID of the student to delete: ");
        String id = input.nextLine().trim();

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("[Error] Student record not found.");
            return;
        }

        studentList.remove(student);
        System.out.println("[Success] Student record deleted completely.");
    }

    // Helper method to find a student by ID
    private static Student findStudentById(String id) {
        for (Student student : studentList) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }
}