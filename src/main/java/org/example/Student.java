package org.example;
public class Student {
    private String id;
    private String studentName;
    private int semester;
    private String courseName;
    public Student() {
    }
    public Student(String id, String studentName, int semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }
    public String getId() {
        return id;
    }
    public String getStudentName() {
        return studentName;
    }

    public int getSemester() {
        return semester;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    @Override
    public String toString() {
        return String.format("ID: %s | Student name: %s | Semester: %d | Course Name: %s",
                id, studentName, semester, courseName);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id != null ? id.equals(student.id) : student.id == null;
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
