import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class DishDetailPage extends JFrame {
    private int rating = 0;

    public DishDetailPage(String name, String desc, String imgPath, String recipe) {
        setTitle(name + " - Details");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        // Title
        JLabel titleLabel = new JLabel(name);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        titleLabel.setBounds(30, 20, 600, 40);
        add(titleLabel);

        // Image
        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(30, 80, 250, 170);
        URL url = getClass().getResource(imgPath);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(250, 170, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(img));
        } else {
            imgLabel.setText("Image Not Found");
        }
        add(imgLabel);

        // Description
        JTextArea descArea = new JTextArea(desc);
        descArea.setBounds(300, 80, 350, 50);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        add(descArea);

        // Recipe
        JTextArea recipeArea = new JTextArea(recipe);
        recipeArea.setBounds(30, 270, 620, 100);
        recipeArea.setFont(new Font("Arial", Font.PLAIN, 14));
        recipeArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);
        recipeArea.setEditable(false);
        recipeArea.setBorder(BorderFactory.createTitledBorder("📖 Recipe"));
        add(recipeArea);

        // Rating (Stars)
        JLabel ratingLabel = new JLabel("⭐ Rate this Dish:");
        ratingLabel.setBounds(30, 390, 200, 25);
        ratingLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(ratingLabel);

        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        starsPanel.setBounds(180, 390, 200, 30);
        JLabel[] stars = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            int index = i;
            stars[i] = new JLabel("☆");
            stars[i].setFont(new Font("SansSerif", Font.PLAIN, 30));
            stars[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            stars[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    rating = index + 1;
                    for (int j = 0; j < 5; j++) {
                        stars[j].setText(j <= index ? "★" : "☆");
                    }
                }
            });
            starsPanel.add(stars[i]);
        }

        add(starsPanel);

        // Feedback
        JLabel feedbackLabel = new JLabel("💬 Your Feedback:");
        feedbackLabel.setBounds(30, 430, 200, 25);
        feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(feedbackLabel);

        JTextArea feedbackArea = new JTextArea();
        feedbackArea.setBounds(30, 460, 620, 50);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        add(feedbackArea);

        // Save Button
        JButton saveBtn = new JButton("💾 Submit Feedback");
        saveBtn.setBounds(250, 520, 200, 30);
        saveBtn.setBackground(new Color(200, 255, 200));
        saveBtn.setFocusPainted(false);
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            String feedback = feedbackArea.getText().trim();
            JOptionPane.showMessageDialog(this,
                    "✅ Thank you!\nRating: " + rating + " stars\nFeedback: " + feedback,
                    "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
