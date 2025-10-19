package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            StudentManagementSystem system = new StudentManagementSystem();
            system.run();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
