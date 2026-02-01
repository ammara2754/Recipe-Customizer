import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CategorySelectionPage extends JFrame {

    public CategorySelectionPage() {
        setTitle("🍱 Select a Food Category");
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ✅ Load Background Image Safely
        JLabel bg = new JLabel();
        ImageIcon bgIcon = safeLoadImage("/Images/images.jpeg", 900, 600);
        if (bgIcon != null) {
            bg.setIcon(bgIcon);
        } else {
            bg.setBackground(Color.DARK_GRAY);
            bg.setOpaque(true);
        }
        setContentPane(bg);
        bg.setLayout(null);

        // ✨ Title
        JLabel title = new JLabel("Choose a Food Category", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 20, 500, 50);
        bg.add(title);

        // 📁 Category Panel
        JPanel panel = new JPanel(new GridLayout(2, 3, 25, 25));
        panel.setBounds(100, 100, 700, 370);
        panel.setOpaque(false);

        String[] categories = {"Italian", "Chinese", "Mexican", "Pakistani", "American", "Vegetarian"};
        String[] imageNames = {"Italian.jpg", "Chinesee.jpg", "Mexican.jpg", "Pakistani.jpg", "Americaan.jpg", "veitarian.jpg"};

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            String imageName = imageNames[i];

            JPanel catPanel = new JPanel();
            catPanel.setLayout(new BorderLayout());
            catPanel.setBackground(new Color(255, 248, 220));
            catPanel.setBorder(BorderFactory.createLineBorder(new Color(210, 105, 30), 2));

            // ✅ Safe Image Load
            ImageIcon icon = safeLoadImage("/Images/" + imageName, 200, 120);
            JLabel imageLabel = new JLabel(icon != null ? icon : new ImageIcon());
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel nameLabel = new JLabel(category, SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
            nameLabel.setForeground(new Color(139, 69, 19));
            nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            catPanel.add(imageLabel, BorderLayout.CENTER);
            catPanel.add(nameLabel, BorderLayout.SOUTH);

            catPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            catPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    new DishesListPage(category).setVisible(true);
                }

                public void mouseEntered(MouseEvent e) {
                    catPanel.setBackground(new Color(255, 236, 200));
                }

                public void mouseExited(MouseEvent e) {
                    catPanel.setBackground(new Color(255, 248, 220));
                }
            });

            panel.add(catPanel);
        }

        bg.add(panel);

        // 🔙 Back Button
        JButton back = new JButton("← Back");
        back.setBounds(20, 500, 100, 35);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setBackground(new Color(255, 200, 100));
        back.setForeground(new Color(90, 40, 10));
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));
        back.addActionListener(e -> dispose());
        bg.add(back);
    }

    // ✅ Safe Image Load Method
    private ImageIcon safeLoadImage(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Image not found: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CategorySelectionPage().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
