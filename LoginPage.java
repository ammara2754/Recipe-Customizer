import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("🔐 Login to Recipe App");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // 🌄 Background Image
        ImageIcon rawBg;
        try {
            rawBg = new ImageIcon(getClass().getResource("/Images/images.jpeg"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Background image not found!");
            rawBg = new ImageIcon();
        }

        Image scaledBg = rawBg.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledBg));
        background.setBounds(0, 0, 1000, 600);
        setContentPane(background);
        background.setLayout(null);

        // 💼 Combined Container Panel
        JPanel container = new JPanel(null);
        container.setBounds(100, 60, 800, 480);
        container.setBackground(new Color(255, 248, 230));
        container.setBorder(BorderFactory.createLineBorder(new Color(210, 105, 30), 2));
        background.add(container);

        // Left Form Panel Inside Container
        JPanel formPanel = new JPanel(null);
        formPanel.setBounds(0, 0, 400, 480);
        formPanel.setBackground(new Color(255, 248, 230));

        JLabel helloLabel = new JLabel("Recipe Customizer");
        helloLabel.setFont(new Font("Arial", Font.BOLD, 28));
        helloLabel.setBounds(80, 30, 300, 40);
        formPanel.add(helloLabel);

        JLabel subtitle = new JLabel("Login to continue");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setBounds(150, 70, 300, 20);
        formPanel.add(subtitle);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 110, 300, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        formPanel.add(usernameField);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(50, 170, 300, 40);
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setBorder(BorderFactory.createTitledBorder("Password"));
        formPanel.add(passField);

        JLabel recovery = new JLabel("Forgot Password?");
        recovery.setFont(new Font("Arial", Font.PLAIN, 12));
        recovery.setForeground(Color.GRAY);
        recovery.setBounds(150, 215, 150, 20);
        formPanel.add(recovery);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 250, 200, 40);
        loginButton.setBackground(new Color(139, 69, 19));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(loginButton);

        container.add(formPanel);

        // Right Panel - Image
        try {
            ImageIcon image = new ImageIcon(getClass().getResource("/Images/Recipe.jpg"));
            Image scaledImg = image.getImage().getScaledInstance(400, 480, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
            imageLabel.setBounds(400, 0, 400, 480);
            container.add(imageLabel);
        } catch (Exception e) {
            System.out.println("❌ Side image not found");
        }

        // Login Logic
        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passField.getPassword());
            if (user.equals("admin") && pass.equals("1234")) {
                new WelcomePage().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Invalid username or password!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
