import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EnterResult extends JFrame {
    JTextField rollField, marksField;
    JComboBox<String> subjectBox;

    public EnterResult() {
        setTitle("📝 Enter Result");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel rollLabel = new JLabel("Roll No:");
        JLabel subjectLabel = new JLabel("Subject:");
        JLabel marksLabel = new JLabel("Marks:");

        rollField = new JTextField(15);
        subjectBox = new JComboBox<>();
        marksField = new JTextField(5);

        loadSubjects();

        JButton saveBtn = new JButton("Save Result");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setBackground(new Color(52, 152, 219));
        saveBtn.setForeground(Color.WHITE);

        saveBtn.addActionListener(e -> saveResult());

        JPanel panel = new JPanel(new GridLayout(4, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(rollLabel); panel.add(rollField);
        panel.add(subjectLabel); panel.add(subjectBox);
        panel.add(marksLabel); panel.add(marksField);
        panel.add(new JLabel("")); panel.add(saveBtn);

        add(panel);
        getContentPane().setBackground(new Color(245, 246, 250));
        setVisible(true);
    }

    private void loadSubjects() {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT subject_name FROM subjects")) {
            while (rs.next()) {
                subjectBox.addItem(rs.getString("subject_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveResult() {
        try (Connection con = DBConnection.getConnection()) {
            String rollNo = rollField.getText();
            String subjectName = (String) subjectBox.getSelectedItem();
            int marks = Integer.parseInt(marksField.getText());

            PreparedStatement ps1 = con.prepareStatement("SELECT subject_id FROM subjects WHERE subject_name=?");
            ps1.setString(1, subjectName);
            ResultSet rs = ps1.executeQuery();
            int subjectId = 0;
            if (rs.next()) subjectId = rs.getInt("subject_id");

            PreparedStatement ps2 = con.prepareStatement("INSERT INTO results (roll_no, subject_id, marks) VALUES (?, ?, ?)");
            ps2.setString(1, rollNo);
            ps2.setInt(2, subjectId);
            ps2.setInt(3, marks);
            ps2.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Result saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
        }
    }
}
