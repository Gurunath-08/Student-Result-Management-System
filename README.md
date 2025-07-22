

##  Student Result Management System

This Java project is a console-based Student Result Management System that allows the user to:

* Add Students
* Add Subjects
* Enter Marks
* Generate Result Reports

###  Project Structure

```
Java project/
├── bin/                # Compiled classes
├── src/srs/            # Java source files
│   ├── Main.java
│   ├── DatabaseConnection.java
│   ├── EnterResult.java
│   ├── HomePage.java
│   ├── ViewResult.java
├── .classpath
├── .project
```

###  How to Run

1. **Requirements:**

   * Java 8 or above
   * MySQL Server
   * JDBC Driver (Connector/J)

2. **Setup:**

   * Create the database and tables using the SQL script below.
   * Update your `DatabaseConnection.java` with your own DB credentials.
   * Compile the source:

     ```bash
     javac -d bin src/srs/*.java
     ```
   * Run the application:

     ```bash
     java -cp bin srs.Main
     ```

---

##  SQL Commands

Run these commands in your MySQL environment to set up the required database and tables:

```sql
CREATE DATABASE student_result_db;
USE student_result_db;

CREATE TABLE students (
    roll_no VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE subjects (
    sub_id VARCHAR(10) PRIMARY KEY,
    sub_name VARCHAR(100)
);

CREATE TABLE marks (
    roll_no VARCHAR(10),
    sub_id VARCHAR(10),
    marks INT,
    FOREIGN KEY (roll_no) REFERENCES students(roll_no),
    FOREIGN KEY (sub_id) REFERENCES subjects(sub_id)
);
```

---

##  Java Classes Overview

### `Main.java`

* The entry point of the application.
* Displays a menu and handles user input.

### `DatabaseConnection.java`

* Manages MySQL database connection using JDBC.

### `EnterResult.java`

* Handles adding students, subjects, and marks.

### `ViewResult.java`

* Generates result reports by fetching and calculating marks from the database.

---

##  Features

* Modular structure (separate classes for input and report).
* JDBC-based SQL operations.
* Console-based menu navigation.

---

![WhatsApp Image 2025-07-22 at 23 38 51_3ce04e2b](https://github.com/user-attachments/assets/ef241f10-4ec9-4191-95d4-ee29b96ae068)

![WhatsApp Image 2025-07-22 at 23 38 51_870258a4](https://github.com/user-attachments/assets/27735087-a10a-4be8-99bb-231423ef1ef3)

![WhatsApp Image 2025-07-22 at 23 38 51_a408ec96](https://github.com/user-attachments/assets/e369a915-62c1-4ef5-b428-2769c1cb875e)
