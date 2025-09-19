import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddSubject extends JFrame {
    JTextField subjectField;

    public AddSubject() {
        setTitle("📘 Add Subject");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel subjectLabel = new JLabel("Subject Name:");
        subjectField = new JTextField(15);

        JButton saveBtn = new JButton("Save Subject");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setBackground(new Color(243, 156, 18));
        saveBtn.setForeground(Color.WHITE);

        saveBtn.addActionListener(e -> saveSubject());

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(subjectLabel); panel.add(subjectField);
        panel.add(new JLabel("")); panel.add(saveBtn);

        add(panel);
        getContentPane().setBackground(new Color(245, 246, 250));
        setVisible(true);
    }

    private void saveSubject() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO subjects (subject_name) VALUES (?)");
            ps.setString(1, subjectField.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Subject added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
        }
    }
}
