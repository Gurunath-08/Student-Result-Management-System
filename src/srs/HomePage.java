import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    private JButton enterResultButton, viewResultButton;

    public HomePage() {
        setTitle("Student Result Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel titleLabel = new JLabel("Student Result Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        add(titleLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(230, 240, 255));

        enterResultButton = new JButton("Enter Result");
        viewResultButton = new JButton("View Result");
        styleButton(enterResultButton);
        styleButton(viewResultButton);

        buttonPanel.add(enterResultButton);
        buttonPanel.add(viewResultButton);
        add(buttonPanel, BorderLayout.SOUTH);

        enterResultButton.addActionListener(e -> {
            new EnterResult(this).setVisible(true);
            setVisible(false);
        });

        viewResultButton.addActionListener(e -> {
            new ViewResult(this).setVisible(true);
            setVisible(false);
        });
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 153, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
