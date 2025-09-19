import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Student Result Management System");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        JLabel titleLabel = new JLabel(" Student Result Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(25, 111, 161));

        // Buttons
        JButton addStudentBtn = styledButton(" Add Student");
        JButton addSubjectBtn = styledButton(" Add Subject");
        JButton enterBtn     = styledButton(" Enter Result");
        JButton viewBtn      = styledButton(" View Result");

        addStudentBtn.addActionListener(e -> new AddStudent());
        addSubjectBtn.addActionListener(e -> new AddSubject());
        enterBtn.addActionListener(e -> new EnterResult());
        viewBtn.addActionListener(e -> new ViewResult());

        // Layout
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 25, 25));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        buttonPanel.setOpaque(false);

        buttonPanel.add(addStudentBtn);
        buttonPanel.add(addSubjectBtn);
        buttonPanel.add(enterBtn);
        buttonPanel.add(viewBtn);

        // Main container
        setLayout(new BorderLayout(20, 20));
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        getContentPane().setBackground(new Color(236, 240, 241));
        setVisible(true);
    }

    // Reusable button style
    private JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(41, 128, 185));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        return btn;
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
