# Student Result Management System

A full-stack Java application to manage student records, subjects, and exam results with a graphical user interface built using **Swing (JFrame)** and backend integration using **MySQL and JDBC**.

## Features

* Add and manage **students**, **subjects**, and **marks**.
* Generate subject-wise result reports with **automatic grade calculation**.
* **Search by Roll Number or Student ID**.
* GUI developed using **Java Swing** for intuitive navigation.
* **MySQL database** used for persistent storage.
* Clean and modular code using **JDBC** for database operations.

## Technologies Used

* Java (JDK 8+)
* Swing (JFrame)
* MySQL
* JDBC

##  Setup Instructions

1. Clone or download the project files.
2. Import the project in your preferred Java IDE (Eclipse, IntelliJ, etc.).
3. Add the MySQL JDBC Connector `.jar` to your project libraries.
4. Create the database and tables using the provided SQL script.
5. Run the `Main.java` file or start from your GUI class.

##  Database Schema Overview

* **students**: Stores student details.
* **subjects**: Stores subject list.
* **marks**: Stores marks for each student and subject.

##  Example SQL Setup

```sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE students (
  student_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  roll_no VARCHAR(20),
  class VARCHAR(10),
  dob DATE
);

CREATE TABLE subjects (
  subject_id INT PRIMARY KEY AUTO_INCREMENT,
  subject_name VARCHAR(100)
);

CREATE TABLE marks (
  mark_id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT,
  subject_id INT,
  marks_obtained INT,
  max_marks INT,
  FOREIGN KEY (student_id) REFERENCES students(student_id),
  FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);
```


