import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewResult extends JFrame {
    JTextField rollField;
    JTextArea resultArea;

    public ViewResult() {
        setTitle("🔍 View Result");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel rollLabel = new JLabel("Enter Roll No:");
        rollField = new JTextField(15);

        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchBtn.setBackground(new Color(155, 89, 182));
        searchBtn.setForeground(Color.WHITE);

        resultArea = new JTextArea(15, 45);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);

        searchBtn.addActionListener(e -> showResults());

        JPanel topPanel = new JPanel();
        topPanel.add(rollLabel);
        topPanel.add(rollField);
        topPanel.add(searchBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        getContentPane().setBackground(new Color(245, 246, 250));
        setVisible(true);
    }

    private void showResults() {
        resultArea.setText("");
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT s.name, sub.subject_name, r.marks " +
                            "FROM results r " +
                            "JOIN students s ON r.roll_no = s.roll_no " +
                            "JOIN subjects sub ON r.subject_id = sub.subject_id " +
                            "WHERE r.roll_no=?");
            ps.setString(1, rollField.getText());
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                String name = rs.getString("name");
                String subject = rs.getString("subject_name");
                int marks = rs.getInt("marks");

                resultArea.append("Name: " + name + "\n");
                resultArea.append("Subject: " + subject + " | Marks: " + marks + "\n");
                resultArea.append("----------------------------------------\n");
            }

            if (!found) {
                resultArea.setText("⚠ No results found for Roll No: " + rollField.getText());
            }
        } catch (Exception e) {
            resultArea.setText("❌ Error fetching results: " + e.getMessage());
        }
    }
}
