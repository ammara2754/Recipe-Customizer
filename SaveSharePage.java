import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class SaveSharePage extends JFrame {
    public SaveSharePage(String recipeText) {
        setTitle("💾 Save or Share Recipe");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 🌄 Main background panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 204, 153)); // soft cream

        // 🏷️ Heading
        JLabel heading = new JLabel("📋 Your Custom Recipe", JLabel.CENTER);
        heading.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        heading.setForeground(new Color(255, 102, 0));
        heading.setBorder(new EmptyBorder(30, 10, 20, 10));
        mainPanel.add(heading, BorderLayout.NORTH);

        // 📋 Recipe display area
        JTextArea recipeArea = new JTextArea(recipeText);
        recipeArea.setFont(new Font("Arial", Font.PLAIN, 16));
        recipeArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);
        recipeArea.setEditable(false);
        recipeArea.setBackground(Color.WHITE);
        recipeArea.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 180, 140), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollPane = new JScrollPane(recipeArea);
        scrollPane.setPreferredSize(new Dimension(600, 250));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel recipeCard = new JPanel();
        recipeCard.setBackground(Color.WHITE);
        recipeCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 193, 112), 3, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        recipeCard.add(scrollPane);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(recipeCard);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 🔘 Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        buttonPanel.setBackground(new Color(255, 204, 153));


        JButton saveButton = new JButton("Save Recipe");
        JButton shareButton = new JButton("Copy Share Link");
        JButton backButton = new JButton("Back to Welcome Page");

        Font btnFont = new Font("Arial", Font.BOLD, 15);
        Color btnBgColor = new Color(255, 140, 0);
        Color btnTextColor = Color.WHITE;
        Color btnHoverColor = new Color(255, 200, 100);

        for (JButton btn : new JButton[]{saveButton, shareButton, backButton}) {
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(200, 45));
            btn.setBackground(btnBgColor);
            btn.setForeground(btnTextColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));

            // ✨ Hover effect
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    btn.setBackground(btnHoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    btn.setBackground(btnBgColor);
                }
            });
        }

        // ✅ Save action
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "✅ Recipe saved successfully!");
            dispose();
        });

        // 📋 Copy action
        shareButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                    new StringSelection("http://share.myrecipe.com/your-custom-recipe"),
                    null
            );
            JOptionPane.showMessageDialog(this, "🔗 Share link copied to clipboard!");
        });

        // 🔙 Back
        backButton.addActionListener(e -> {
            new WelcomePage().setVisible(true);
            dispose();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(shareButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }
}
