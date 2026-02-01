import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class RecipeResultPage extends JFrame {

    public RecipeResultPage(String title, String imageUrl, List<String> ingredients, String instructions) {
        setTitle("🍲 Your Recipe");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(255, 204, 153));

        // Title Label
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        titleLabel.setForeground(new Color(255, 102, 0));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        // Recipe Image
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon icon = new ImageIcon(new URL(imageUrl));
            Image img = icon.getImage().getScaledInstance(400, 280, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("Image Not Available");
            imageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            imageLabel.setForeground(Color.DARK_GRAY);
        }

        // Ingredients Section
        JPanel ingredientsPanel = new JPanel(new BorderLayout());
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder(null, "🧂 Ingredients", 0, 0,
                new Font("Segoe UI", Font.BOLD, 18), Color.BLACK));
        ingredientsPanel.setBackground(new Color(255, 239, 200));

        JTextArea ingredientsArea = new JTextArea();
        ingredientsArea.setEditable(false);
        ingredientsArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        ingredientsArea.setBackground(new Color(255, 239, 200));

        for (String ing : ingredients) {
            ingredientsArea.append("➤ " + ing + "\n");
        }

        ingredientsPanel.add(new JScrollPane(ingredientsArea), BorderLayout.CENTER);

        // Instructions Section
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBorder(BorderFactory.createTitledBorder(null, "👨‍🍳 Instructions", 0, 0,
                new Font("Segoe UI", Font.BOLD, 18), Color.BLACK));
        instructionsPanel.setBackground(new Color(255, 239, 200));

        JTextArea instructionsArea = new JTextArea(instructions);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setLineWrap(true);
        instructionsArea.setEditable(false);
        instructionsArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        instructionsArea.setBackground(new Color(255, 239, 200));

        instructionsPanel.add(new JScrollPane(instructionsArea), BorderLayout.CENTER);

        // Center Content Panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBackground(new Color(255, 204, 153));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(ingredientsPanel);
        centerPanel.add(instructionsPanel);

        // Button
        JButton backButton = new JButton("🔙 Back");
        JButton feedbackButton = new JButton("💬 Give Feedback");
        JButton saveShareButton = new JButton("📤 Save");

        // 🎨 Style all buttons the same
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
        Color buttonColor = new Color(255, 140, 0);
        Color textColor = Color.WHITE;
        Dimension buttonSize = new Dimension(160, 40);

        JButton[] buttons = { backButton, feedbackButton, saveShareButton };
        for (JButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(buttonSize);
        }

        // 🔁 Add ActionListeners
        backButton.addActionListener(e -> {
            dispose();
            new RecipeGeneratorPage(); // make sure this class exists
        });

        feedbackButton.addActionListener(e -> {
            new FeedbackPage().setVisible(true); // make sure this class exists
        });

        saveShareButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "✅ Recipe saved successfully!",
                    "Saved",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });


        // ⬇️ Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(255, 204, 153));
        bottomPanel.add(backButton);
        bottomPanel.add(feedbackButton);
        bottomPanel.add(saveShareButton);


    // Layout Assembly
        add(titleLabel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}