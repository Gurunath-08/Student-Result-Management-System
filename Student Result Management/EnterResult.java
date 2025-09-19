import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EnterResult extends JFrame {
    private JTextField idField, nameField, subjectField, marksField, gradeField;
    private JButton submitButton, backButton;
    private JFrame homeFrame;

    public EnterResult(JFrame homeFrame) {
        this.homeFrame = homeFrame;

        setTitle("Enter Student Result");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 250, 240));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 250, 240));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backButton = new JButton("← Back");
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.addActionListener(e -> {
            homeFrame.setVisible(true);
            dispose();
        });

        JLabel titleLabel = new JLabel("Enter Student Result", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 153));

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        formPanel.setBackground(new Color(255, 250, 240));

        idField = new JTextField();
        nameField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();
        gradeField = new JTextField();

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Subject:"));
        formPanel.add(subjectField);
        formPanel.add(new JLabel("Marks (0–100):"));
        formPanel.add(marksField);
        formPanel.add(new JLabel("Grade (A–F):"));
        formPanel.add(gradeField);
        add(formPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 153, 76));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 250, 240));
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        restrictInputFields();
        submitButton.addActionListener(e -> handleSubmit());
    }

    private void restrictInputFields() {
        ((PlainDocument) marksField.getDocument()).setDocumentFilter(new DocumentFilter() {
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= 3 && string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= 3 && text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        ((PlainDocument) gradeField.getDocument()).setDocumentFilter(new DocumentFilter() {
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[A-Fa-f]") && fb.getDocument().getLength() == 0)
                    super.insertString(fb, offset, string.toUpperCase(), attr);
            }
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[A-Fa-f]?"))
                    super.replace(fb, offset, length, text.toUpperCase(), attrs);
            }
        });
    }

    private void handleSubmit() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String subject = subjectField.getText().trim();
        String marksText = marksField.getText().trim();
        String grade = gradeField.getText().trim().toUpperCase();

        if (id.isEmpty() || name.isEmpty() || subject.isEmpty() || marksText.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            int marks = Integer.parseInt(marksText);
            if (marks < 0 || marks > 999) throw new NumberFormatException();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO marks VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, subject);
            ps.setInt(4, marks);
            ps.setString(5, grade);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Result Saved Successfully!");
            clearFields();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be a number between 0–100.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        subjectField.setText("");
        marksField.setText("");
        gradeField.setText("");
    }
}
