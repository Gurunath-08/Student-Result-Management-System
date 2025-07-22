package srs;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection c;
        while (true) {
            System.out.println("\n--- Student Result Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Subject");
            System.out.println("3. Add Marks");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    // Add student
                    System.out.print("Roll Number: ");
                    String roll = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Class: ");
                    String className = sc.nextLine();
                    System.out.print("Date of Birth (yyyy-mm-dd): ");
                    String dob = sc.nextLine();

                    c = DatabaseConnection.getConnection();
                    PreparedStatement pst = c.prepareStatement(
                            "INSERT INTO students (roll_no, name, class, dob) VALUES (?, ?, ?, ?)");
                    pst.setString(1, roll);
                    pst.setString(2, name);
                    pst.setString(3, className);
                    pst.setDate(4, Date.valueOf(dob));
                    pst.executeUpdate();
                    c.close();

                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    // Add subject
                    System.out.print("Subject Code: ");
                    String subCode = sc.nextLine();
                    System.out.print("Subject Name: ");
                    String subName = sc.nextLine();

                    c = DatabaseConnection.getConnection();
                    pst = c.prepareStatement(
                            "INSERT INTO subjects (subject_code, subject_name) VALUES (?, ?)");
                    pst.setString(1, subCode);
                    pst.setString(2, subName);
                    pst.executeUpdate();
                    c.close();

                    System.out.println("Subject added successfully.");
                    break;

                case 3:
                    // Add marks
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Subject Code: ");
                    String subjectCode = sc.nextLine();
                    System.out.print("Marks Obtained: ");
                    int marks = sc.nextInt();
                    System.out.print("Max Marks: ");
                    int max = sc.nextInt();

                    c = DatabaseConnection.getConnection();

                    // Fetch subject ID
                    PreparedStatement pst1 = c.prepareStatement(
                            "SELECT subject_id FROM subjects WHERE subject_code = ?");
                    pst1.setString(1, subjectCode);
                    ResultSet rs = pst1.executeQuery();

                    if (rs.next()) {
                        int subid = rs.getInt("subject_id");
                        rs.close();
                        pst1.close();

                        // Insert marks
                        PreparedStatement pst2 = c.prepareStatement(
                                "INSERT INTO marks(student_id, subject_id, marks_obtained, max_marks) VALUES (?, ?, ?, ?)");
                        pst2.setInt(1, sid);
                        pst2.setInt(2, subid);
                        pst2.setInt(3, marks);
                        pst2.setInt(4, max);
                        pst2.executeUpdate();
                        pst2.close();

                        System.out.println("Marks inserted successfully.");
                    } else {
                        System.out.println("Subject not found.");
                    }

                    c.close();
                    break;

                case 4:
                    // Generate report
                    System.out.print("Enter Roll Number: ");
                    String rollNo = sc.nextLine();
                    ReportGenerator.generateReport(rollNo);
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
