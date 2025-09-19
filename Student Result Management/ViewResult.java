import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewResult extends JFrame {
    private JTextField idField;
    private JButton searchButton, backButton;
    private JTextArea resultArea;
    private JFrame homeFrame;

    public ViewResult(JFrame homeFrame) {
        this.homeFrame = homeFrame;

        setTitle("View Student Result");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 255, 250));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 255, 250));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backButton = new JButton("← Back");
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.addActionListener(e -> {
            homeFrame.setVisible(true);
            dispose();
        });

        JLabel titleLabel = new JLabel("View Student Result", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 153));

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        formPanel.setBackground(new Color(245, 255, 250));

        idField = new JTextField(15);
        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(30, 144, 255));
        searchButton.setForeground(Color.WHITE);

        formPanel.add(new JLabel("Enter Student ID:"));
        formPanel.add(idField);
        formPanel.add(searchButton);
        add(formPanel, BorderLayout.CENTER);

        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Result"));
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchResult());
    }

    private void searchResult() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Student ID!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM marks WHERE student_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultArea.setText(
                        "ID: " + rs.getString("student_id") + "\n" +
                                "Name: " + rs.getString("name") + "\n" +
                                "Subject: " + rs.getString("subject") + "\n" +
                                "Marks: " + rs.getInt("marks") + "\n" +
                                "Grade: " + rs.getString("grade")
                );
            } else {
                resultArea.setText("No result found for ID: " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
