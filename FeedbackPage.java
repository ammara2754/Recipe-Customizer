import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FeedbackPage extends JFrame {
    public FeedbackPage() {
        setTitle("📢 Feedback");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 204, 153)); // Light background

        JLabel heading = new JLabel("📝 We value your Feedback!", JLabel.CENTER);
        heading.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        heading.setForeground(new Color(255, 102, 0));
        heading.setBorder(new EmptyBorder(30, 10, 20, 10));
        mainPanel.add(heading, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // 🖼️ Feedback image (updated size)
        ImageIcon icon = new ImageIcon("src/images/feedback.png"); // ✅ Ensure correct path
        Image scaledImage = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH); // updated width = 250
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        centerPanel.add(imageLabel, gbc);

        // 📝 Feedback Text Area
        JTextArea feedbackArea = new JTextArea(6, 40);
        feedbackArea.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setBackground(Color.WHITE);
        feedbackArea.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 180, 140), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setPreferredSize(new Dimension(600, 180));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel feedbackCard = new JPanel();
        feedbackCard.setBackground(Color.WHITE);
        feedbackCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 193, 112), 3, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        feedbackCard.add(scrollPane);

        gbc.gridy = 1;
        centerPanel.add(feedbackCard, gbc);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 204, 153));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        JButton submitButton = new JButton("✅ Submit & Exit");
        JButton backButton = new JButton("🔙 Back to Welcome Page");

        Font btnFont = new Font("Arial", Font.BOLD, 15);
        Color btnBgColor = new Color(255, 140, 0);
        Color btnTextColor = Color.WHITE;
        Color btnHoverColor = new Color(255, 200, 100);

        for (JButton btn : new JButton[]{submitButton, backButton}) {
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(200, 45));
            btn.setBackground(btnBgColor);
            btn.setForeground(btnTextColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));

            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(btnHoverColor);
                }

                public void mouseExited(MouseEvent e) {
                    btn.setBackground(btnBgColor);
                }
            });
        }

        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "✅ Thank you for your feedback!");
            dispose();
        });

        backButton.addActionListener(e -> {
            new WelcomePage().setVisible(true);
            dispose();
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }
}