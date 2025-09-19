import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddStudent extends JFrame {
    JTextField rollField, nameField;

    public AddStudent() {
        setTitle("➕ Add Student");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel rollLabel = new JLabel("Roll No:");
        JLabel nameLabel = new JLabel("Name:");

        rollField = new JTextField(15);
        nameField = new JTextField(15);

        JButton saveBtn = new JButton("Save Student");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setBackground(new Color(39, 174, 96));
        saveBtn.setForeground(Color.WHITE);

        saveBtn.addActionListener(e -> saveStudent());

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(rollLabel); panel.add(rollField);
        panel.add(nameLabel); panel.add(nameField);
        panel.add(new JLabel("")); panel.add(saveBtn);

        add(panel);
        getContentPane().setBackground(new Color(245, 246, 250));
        setVisible(true);
    }

    private void saveStudent() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO students (roll_no, name) VALUES (?, ?)");
            ps.setString(1, rollField.getText());
            ps.setString(2, nameField.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Student added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
        }
    }
}
