import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomRecipePage extends JFrame {
    public CustomRecipePage() {
        setTitle(" Create Your Custom Recipe");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 🌄 Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 229, 180)); // Soft peach

        // 🏧 Heading
        JLabel heading = new JLabel(" Build Your Custom Recipe", JLabel.CENTER);
        heading.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        heading.setForeground(new Color(255, 102, 0));
        heading.setBorder(new EmptyBorder(30, 10, 20, 10));
        mainPanel.add(heading, BorderLayout.NORTH);

        // 📋 Center panel with two text areas
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 20, 20));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        // 🥕 Ingredients text area
        JTextArea ingredientsArea = new JTextArea();
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        ingredientsArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        ingredientsArea.setToolTipText("Enter ingredients here...");

        JScrollPane ingredientsScroll = new JScrollPane(ingredientsArea);
        ingredientsScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210, 180, 140), 2, true),
                "🡢 Ingredients",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                new Color(153, 51, 0)
        ));

        // 📋 Recipe steps area
        JTextArea recipeStepsArea = new JTextArea();
        recipeStepsArea.setLineWrap(true);
        recipeStepsArea.setWrapStyleWord(true);
        recipeStepsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        recipeStepsArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        recipeStepsArea.setToolTipText("Write cooking steps here...");

        JScrollPane stepsScroll = new JScrollPane(recipeStepsArea);
        stepsScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210, 180, 140), 2, true),
                "👨‍🍳 Recipe Steps",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                new Color(153, 51, 0)
        ));

        centerPanel.add(ingredientsScroll);
        centerPanel.add(stepsScroll);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 🔘 Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        buttonPanel.setBackground(new Color(255, 229, 180));

        JButton feedbackButton = new JButton("Give Feedback");
        JButton saveShareButton = new JButton("Save & Share");
        JButton backButton = new JButton("Back to Welcome Page");

        Font btnFont = new Font("Arial", Font.BOLD, 15);
        Color btnBgColor = new Color(255, 140, 0);
        Color btnTextColor = Color.WHITE;
        Color btnHoverColor = new Color(255, 200, 100);

        for (JButton btn : new JButton[]{feedbackButton, saveShareButton, backButton}) {
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(200, 45));
            btn.setBackground(btnBgColor);
            btn.setForeground(btnTextColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    btn.setBackground(btnHoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    btn.setBackground(btnBgColor);
                }
            });
        }

        // 🎯 Button actions
        feedbackButton.addActionListener(e -> {
            new FeedbackPage().setVisible(true);
        });

        saveShareButton.addActionListener(e -> {
            String fullRecipe = "-> Ingredients:\n" + ingredientsArea.getText().trim()
                    + "\n\n-> Steps:\n" + recipeStepsArea.getText().trim();

            new SaveSharePage(fullRecipe).setVisible(true);
            dispose();
        });

        backButton.addActionListener(e -> {
            new WelcomePage().setVisible(true);
            dispose();
        });

        buttonPanel.add(feedbackButton);
        buttonPanel.add(saveShareButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }
}