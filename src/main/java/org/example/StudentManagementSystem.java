package org.example;

import java.util.*;

public class StudentManagementSystem {
    private StudentManager studentManager;
    private Scanner scanner;

    public StudentManagementSystem() {
        this.studentManager = new StudentManager();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");

        while (true) {
            displayMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSortStudents();
                    break;
                case 3:
                    updateOrDeleteStudent();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Thank you for using the program!");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose from 1-5.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("=".repeat(50));
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println("=".repeat(50));
        System.out.print("(Please select 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit): ");    }

    private int getChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.print("Please enter a number from 1-5: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private void createStudent() {
        System.out.println("\n--- CREATE NEW STUDENT ---");

        int studentCount = 0;

        while (true) {
            System.out.println("\nStudent number " + (studentCount + 1) + ":");

            String id = getStudentId();
            String name = getStudentName();
            int semester = getSemester();
            String course = getCourseName();

            Student newStudent = new Student(id, name, semester, course);

            if (studentManager.addStudent(newStudent)) {
                System.out.println("Student added successfully!");
                studentCount++;
            } else {
                System.out.println("Error: Student with ID '\" + id + \"' already exists!");
                continue;
            }

            if (studentCount >= 10) {
                System.out.print("\nYou have created 10 students. Do you want to continue (Y/N)? ");
                String continueChoice = scanner.nextLine().trim().toUpperCase();
                if (!continueChoice.equals("Y")) {
                    break;
                }
            }
        }

        System.out.println("\nTotal number of students created: " + studentCount);
    }

    private String getStudentId() {
        while (true) {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine().trim();
            if (!id.isEmpty()) {
                return id;
            }
            System.out.println("ID cannot be empty!");
        }
    }

    private String getStudentName() {
        while (true) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Student name cannot be empty!");
        }
    }

    private int getSemester() {
        while (true) {
            try {
                System.out.print("Admission semester: ");
                int semester = Integer.parseInt(scanner.nextLine().trim());
                if (semester > 0) {
                    return semester;
                }
                System.out.println("Semester must be a positive integer!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private String getCourseName() {
        String[] allowedCourses = StudentManager.getAllowedCourses();

        while (true) {
            System.out.println("Select course:");
            for (int i = 0; i < allowedCourses.length; i++) {
                System.out.println((i + 1) + ". " + allowedCourses[i]);
            }
            System.out.print("Enter selection (1-" + allowedCourses.length + "): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= allowedCourses.length) {
                    return allowedCourses[choice - 1];
                }
                System.out.println("Please select from 1 to " + allowedCourses.length);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private void findAndSortStudents() {
        System.out.println("\n--- STUDENT SEARCH AND PLACEMENT ---");

        if (studentManager.isEmpty()) {
            System.out.println("Empty (student)");
            return;
        }

        System.out.print("Enter the name of the student you want to find (you can enter part of the name): ");
        String searchName = scanner.nextLine().trim();

        if (searchName.isEmpty()) {
            System.out.println("Search name cannot be empty!");
            return;
        }

        List<Student> foundStudents = studentManager.findStudentsByName(searchName);

        if (foundStudents.isEmpty()) {
            System.out.println("No students found with the name: " + searchName);
            return;
        }

        Collections.sort(foundStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
            }
        });

        System.out.println("\nSearch result (sorted by name):");
        System.out.println("-".repeat(80));
        System.out.printf("%-20s %-15s %-15s%n", "Student name", "Semester", "Cours name");
        System.out.println("-".repeat(80));

        for (Student student : foundStudents) {
            System.out.printf("%-20s %-15d %-15s%n",
                    student.getStudentName(),
                    student.getSemester(),
                    student.getCourseName());
        }

        System.out.println("-".repeat(80));
        System.out.println(foundStudents.size() + " students.");
    }

    private void updateOrDeleteStudent() {
        System.out.println("\n--- UPDATE/DELETE STUDENTS ---");

        if (studentManager.isEmpty()) {
            System.out.println("Empty(student)");
            return;
        }

        System.out.print("Enter the student ID you are looking for: ");
        String id = scanner.nextLine().trim();

        Student student = studentManager.findStudentById(id);
        if (student == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        System.out.println("\nStudent information:");
        System.out.println(student.toString());

        System.out.print("\nDo you want to update (U) or delete (D) student.\nIf user chooses U, the program allows user updating.\nChoose D for deleting student. ");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "U":
                updateStudent(student);
                break;
            case "D":
                deleteStudent(student);
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    private void updateStudent(Student student) {
        System.out.println("\n--- UPDATE STUDENT INFORMATION ---");

        System.out.println("Enter new information (leave blank if you do not want to change):");

        System.out.print("New student name [" + student.getStudentName() + "]: ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            student.setStudentName(newName);
        }

        System.out.print("New semester [" + student.getSemester() + "]: ");
        String semesterInput = scanner.nextLine().trim();
        if (!semesterInput.isEmpty()) {
            try {
                int newSemester = Integer.parseInt(semesterInput);
                if (newSemester > 0) {
                    student.setSemester(newSemester);
                } else {
                    System.out.println("Semester must be a positive integer!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid semester!");
            }
        }

        System.out.println("New course [" + student.getCourseName() + "]:");
        String[] allowedCourses = StudentManager.getAllowedCourses();
        for (int i = 0; i < allowedCourses.length; i++) {
            System.out.println((i + 1) + ". " + allowedCourses[i]);
        }
        System.out.print("Enter options (leave blank if not changed): ");
        String courseInput = scanner.nextLine().trim();
        if (!courseInput.isEmpty()) {
            try {
                int courseChoice = Integer.parseInt(courseInput);
                if (courseChoice >= 1 && courseChoice <= allowedCourses.length) {
                    student.setCourseName(allowedCourses[courseChoice - 1]);
                } else {
                    System.out.println("Invalid selection!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection!");
            }
        }

        System.out.println("\nStudent information updated successfully!");
        System.out.println("New: " + student.toString());
    }

    private void deleteStudent(Student student) {
        System.out.print("Do you want to continue (Y/N)? ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            if (studentManager.deleteStudent(student.getId())) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Error deleting student!");
            }
        } else {
            System.out.println("Delete operation canceled.");
        }
    }

    private void generateReport() {
        System.out.println("\n--- STUDENT STATISTICS REPORT ---");

        if (studentManager.isEmpty()) {
            System.out.println("Empty (student)!");
            return;
        }

        Map<String, Map<String, Integer>> report = studentManager.generateReport();

        System.out.println("\nReport:");
        System.out.println("-".repeat(60));
        System.out.printf("%-25s %-15s %-15s%n", "Student Name", "Course Name", "Total (course)");
        System.out.println("-".repeat(60));

        List<String> sortedStudentNames = new ArrayList<>(report.keySet());
        Collections.sort(sortedStudentNames);

        for (String studentName : sortedStudentNames) {
            Map<String, Integer> studentCourses = report.get(studentName);

            List<String> sortedCourses = new ArrayList<>(studentCourses.keySet());
            Collections.sort(sortedCourses);

            for (String courseName : sortedCourses) {
                int courseCount = studentCourses.get(courseName);
                System.out.printf("%-25s %-15s %-15d%n", studentName, courseName, courseCount);
            }
        }

        System.out.println("-".repeat(60));
        System.out.println("Total (student): " + report.size());
    }
}
