package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students;
    private static final String[] ALLOWED_COURSES = {"Java", ".Net", "C/C++"};

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (students.contains(student)) {
            return false;
        }
        students.add(student);
        return true;
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudentsByName(String name) {
        return students.stream()
                .filter(student -> student.getStudentName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByName() {
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
            }
        });
        return sortedStudents;
    }

    public boolean updateStudent(String id, Student newStudent) {
        Student existingStudent = findStudentById(id);
        if (existingStudent != null) {
            existingStudent.setStudentName(newStudent.getStudentName());
            existingStudent.setSemester(newStudent.getSemester());
            existingStudent.setCourseName(newStudent.getCourseName());
            return true;
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        Student studentToRemove = findStudentById(id);
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public int getStudentCount() {
        return students.size();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public static String[] getAllowedCourses() {
        return ALLOWED_COURSES.clone();
    }

    public static boolean isValidCourse(String courseName) {
        for (String course : ALLOWED_COURSES) {
            if (course.equals(courseName)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Map<String, Integer>> generateReport() {
        Map<String, Map<String, Integer>> report = new HashMap<>();

        for (Student student : students) {
            String studentName = student.getStudentName();
            String courseName = student.getCourseName();

            if (!report.containsKey(studentName)) {
                report.put(studentName, new HashMap<>());
            }

            Map<String, Integer> studentCourses = report.get(studentName);
            studentCourses.put(courseName, studentCourses.getOrDefault(courseName, 0) + 1);
        }

        return report;
    }
}
