import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WelcomePage extends JFrame {

    private JLabel sliderLabel;
    private int currentImage = 0;

    private String[] images = {
            "Images/pexels-fariphotography-905847.jpg",
            "Images/pexels-valeriya-32558924.jpg",
            "Images/pexels-unkdevil-19130144.jpg",
            "Images/pexels-ella-olsson-572949-3026808.jpg"
    };
    private Timer sliderTimer;

    public WelcomePage() {
        setTitle("🍽️ Welcome to Recipe Customizer");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 🌄 Background image
        ImageIcon rawBg;
        try {
            rawBg = new ImageIcon(getClass().getResource("/Images/images.jpeg"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Background image not found!");
            rawBg = new ImageIcon(); // empty fallback
        }

        Image scaledBg = rawBg.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledBg));
        background.setBounds(0, 0, 900, 600);
        setContentPane(background);
        background.setLayout(null);

        // ✨ Title
        JLabel title = new JLabel(" Welcome to Recipe Customizer ", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        title.setForeground(new Color(255, 223, 128));
        title.setBounds(200, 10, 500, 40);
        background.add(title);

        // 📝 Subtitle
        JLabel subtitle = new JLabel("Create or View Delicious Recipes Instantly!", JLabel.CENTER);
        subtitle.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        subtitle.setForeground(new Color(255, 191, 0));
        subtitle.setBounds(200, 50, 500, 30);
        background.add(subtitle);

        // 📸 Image Slider
        sliderLabel = new JLabel();
        sliderLabel.setBounds(200, 100, 500, 280);
        sliderLabel.setHorizontalAlignment(JLabel.CENTER);
        setSliderImage();
        background.add(sliderLabel);

        // ⏲️ Slider timer
        sliderTimer = new Timer(2500, e -> setSliderImage());
        sliderTimer.start();

        // 🔘 Buttons - Row 1 (2 buttons side by side)
        int buttonWidth = 180;
        int gap = 40;
        int totalWidth = buttonWidth * 2 + gap;
        int startX = (900 - totalWidth) / 2;

        JButton generatorBtn = createStyledButton("Recipe Generator", startX, 420);
        generatorBtn.addActionListener(e -> new RecipeGeneratorPage().setVisible(true));

        JButton readymadeBtn = createStyledButton("Readymade Recipes", startX + buttonWidth + gap, 420);
        readymadeBtn.addActionListener(e -> new CategorySelectionPage().setVisible(true));

        // 🔘 Row 2 - Custom Recipe Button (centered)
        JButton customrecipeBtn = createStyledButton("Custom Recipes", (900 - buttonWidth) / 2, 470);
        customrecipeBtn.addActionListener(e -> new CustomRecipePage().setVisible(true));

        background.add(generatorBtn);
        background.add(readymadeBtn);
        background.add(customrecipeBtn);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 180, 40);
        btn.setBackground(new Color(255, 223, 128));
        btn.setForeground(new Color(80, 40, 0));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));
        btn.setOpaque(true);
        return btn;
    }

    private void setSliderImage() {
        try {
            java.net.URL imgURL = getClass().getResource("/" + images[currentImage]);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(500, 280, Image.SCALE_SMOOTH);
                sliderLabel.setIcon(new ImageIcon(img));
                sliderLabel.setText("");
            } else {
                sliderLabel.setText("Image not found: " + images[currentImage]);
            }
        } catch (Exception e) {
            sliderLabel.setText("Error loading image.");
        }

        currentImage = (currentImage + 1) % images.length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
