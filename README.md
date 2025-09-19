******📚 Student Result Management System******


A Java Swing + MySQL desktop application to manage student records, subjects, and exam results with a clean and modern graphical user interface.




****✨ Features****



➕ Add Student – Register new students with roll numbers and names.


📘 Add Subject – Add subjects dynamically into the database.


📝 Enter Result – Record marks for a student in any subject.


🔍 View Result – Search and view results of a student with subject-wise marks.


🎨 Modern GUI – Styled with consistent colors, fonts, and layouts for an appealing experience.




****🖥️ Tech Stack****



Java (Swing, AWT) – GUI development

MySQL – Database backend

JDBC (mysql-connector) – Database connectivity

Maven / IntelliJ IDEA / Eclipse – (any IDE works)




**📂 Project Structure**

Student Result Management/
│── HomePage.java        # Main dashboard window
│── AddStudent.java      # Add student form
│── AddSubject.java      # Add subject form
│── EnterResult.java     # Enter result form
│── ViewResult.java      # View result window
│── DBConnection.java    # Centralized DB connection helper
│── Main.java            # Entry point
│── mysql-connector-j-9.3.0.jar  # MySQL JDBC Driver
│── Student Result Management.iml
└── README.md




****🛠️ Setup Instructions****


**1️⃣ Database Setup**

Open MySQL and create a new database:

CREATE DATABASE student_db;
USE student_db;

-- Table for students
CREATE TABLE students (
    roll_no VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table for subjects
CREATE TABLE subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL
);

-- Table for results
CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roll_no VARCHAR(20),
    subject_id INT,
    marks INT,
    FOREIGN KEY (roll_no) REFERENCES students(roll_no),
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);



**2️⃣ Project Configuration**

Clone or copy the project folder.

Open it in your IDE (IntelliJ / Eclipse / NetBeans).

Add the MySQL connector JAR (mysql-connector-j-9.3.0.jar) to your project’s classpath:

IntelliJ: File → Project Structure → Libraries → Add JAR

Eclipse: Right Click Project → Build Path → Add External Archives

Update DBConnection.java with your MySQL username & password. Example:

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";   // 👈 your MySQL username
        String pass = "password"; // 👈 your MySQL password
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}



**3️⃣ Run the Application**

Run Main.java

The HomePage will appear with four options:

➕ Add Student

📘 Add Subject

📝 Enter Result

🔍 View Result

📸 Screenshots



**Home Page **



<img width="862" height="555" alt="Screenshot 2025-09-19 223545" src="https://github.com/user-attachments/assets/d6a35500-77ed-49ef-8d54-2e8b6d10bfe2" />





**Add Student / Add Subject – Simple forms with styled buttons**



<img width="857" height="548" alt="Screenshot 2025-09-19 223558" src="https://github.com/user-attachments/assets/b9a32a88-9d10-4ccc-a70f-85849018bfcb" />



<img width="855" height="549" alt="Screenshot 2025-09-19 223610" src="https://github.com/user-attachments/assets/adc08d1a-507c-4ddb-9e7b-c9676ec6ac67" />




**Enter Result – Drop-down to choose subject, enter marks**



<img width="856" height="550" alt="Screenshot 2025-09-19 223623" src="https://github.com/user-attachments/assets/576ce14e-4cc2-4a68-abf9-0edcef51ceca" />




**View Result – Displays student details and subject-wise marks**



<img width="864" height="558" alt="Screenshot 2025-09-19 223707" src="https://github.com/user-attachments/assets/1e8bea8c-babb-414e-9b09-900162c7b032" />






****🚀 Future Enhancements****

Export results to PDF/Excel.

Admin login authentication.

Graphical performance reports (charts).

Dark mode theme 🌙.





****👨‍💻 Author****

Developed with ❤️ in Java & MySQL.
