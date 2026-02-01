import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import org.json.JSONArray;
import javax.swing.Timer;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public class RecipeGeneratorPage extends JFrame {
    private JPanel selectedPanel;
    private Set<String> selectedIngredients = new HashSet<>();
    private Map<String, String> emojiMap;
    private JScrollPane scrollPane;

    public RecipeGeneratorPage() {
        setTitle("🍽️ Recipe Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        emojiMap = createEmojiMap();

        JLabel title = new JLabel("Select Your Ingredients", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Fruits 🍎", createCategoryPanel(new String[]{
                "Apple", "Banana", "Strawberry", "Mango", "Orange", "Pineapple",
                "Grapes", "Blueberry", "Watermelon", "Peach", "Kiwi", "Avocado", "Guava"
        }));
        tabbedPane.addTab("Vegetables 🥕", createCategoryPanel(new String[]{
                "Carrot", "Tomato", "Garlic", "Corn", "Potato", "Onion", "Spinach",
                "Broccoli", "Cucumber", "Peas", "Mushroom", "Eggplant", "Hot Pepper", "Sweet Potato"
        }));
        tabbedPane.addTab("Dairy 🥛", createCategoryPanel(new String[]{
                "Milk", "Cheese", "Yogurt", "Butter", "Cream", "Paneer", "Ghee"
        }));
        tabbedPane.addTab("Meat 🍗", createCategoryPanel(new String[]{
                "Chicken", "Egg", "Beef", "Mutton", "Fish", "Turkey", "Prawns"
        }));
        tabbedPane.addTab("Nuts 🥜", createCategoryPanel(new String[]{
                "Almonds", "Cashews", "Walnuts", "Peanuts", "Pistachios", "Hazelnuts"
        }));
        tabbedPane.addTab("Others 🌟", createCategoryPanel(new String[]{
                "Water", "Salt", "Sugar", "Honey", "Lemon"
        }));
        add(tabbedPane, BorderLayout.CENTER);

        selectedPanel = new JPanel(null);
        selectedPanel.setPreferredSize(new Dimension(0, 100));
        selectedPanel.setBackground(new Color(255, 248, 220));
        selectedPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0), 2));

        scrollPane = new JScrollPane(selectedPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(0, 100));
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.SOUTH);


        // Right-side panel for navigation (vertical layout)
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(new Color(255, 140, 0));
        navPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20)); // padding

// Cook Button
        JButton cookButton = new JButton("👨‍🍳 Cook");
        cookButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        cookButton.setBackground(new Color(255, 102, 0));
        cookButton.setForeground(Color.WHITE);
        cookButton.setFocusPainted(false);
        cookButton.setPreferredSize(new Dimension(120, 45));
        cookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cookButton.addActionListener(e -> cookRecipe());

// Back Button (placed below cook button)
        JButton backButton = new JButton("🔙 Back");
        backButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 102, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(120, 45));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            dispose();
            new WelcomePage().setVisible(true);
        });

// Add spacing between buttons
        navPanel.add(cookButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20))); // vertical space
        navPanel.add(backButton);

        add(navPanel, BorderLayout.EAST);

    }

    private JPanel createCategoryPanel(String[] ingredients) {
        int cols = 5;
        JPanel panel = new JPanel(new GridLayout(0, cols, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 204, 153));

        for (String ing : ingredients) {
            String emoji = emojiMap.getOrDefault(ing, "❓");
            JButton btn = new JButton("<html><div style='text-align:center;font-size:30px;color:darkorange;'>" + emoji +
                    "</div><div style='text-align:center;font-size:12px;color:black;'>" + ing + "</div></html>");
            btn.setBackground(new Color(255, 204, 153));
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
            btn.setForeground(Color.DARK_GRAY);

            btn.addActionListener(e -> {
                if (selectedIngredients.contains(ing)) {
                    selectedIngredients.remove(ing);
                    for (Component comp : selectedPanel.getComponents()) {
                        if (comp instanceof JLabel) {
                            JLabel lbl = (JLabel) comp;
                            if (lbl.getText().equals(emoji)) {
                                selectedPanel.remove(lbl);
                                break;
                            }
                        }
                    }
                    selectedPanel.revalidate();
                    selectedPanel.repaint();
                } else {
                    animateSelection(ing);
                }
            });

            panel.add(btn);
        }

        return panel;
    }

    private void animateSelection(String ing) {
        JLabel label = new JLabel(emojiMap.getOrDefault(ing, "❓"));
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        label.setForeground(Color.ORANGE);
        int x = 15 + selectedPanel.getComponentCount() * 50;
        label.setBounds(x, 0, 50, 50);
        selectedPanel.add(label);
        selectedPanel.repaint();

        Timer timer = new Timer(8, null);
        timer.addActionListener(new ActionListener() {
            int y = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                y += 3;
                label.setLocation(x, y);
                if (y >= 30) {
                    timer.stop();
                    selectedIngredients.add(ing);
                    selectedPanel.revalidate();
                    selectedPanel.repaint();
                }
            }
        });

        timer.start();
    }

    private void cookRecipe() {
        if (selectedIngredients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one ingredient!");
            return;
        }

        try {
            String apiKey = "d1bc297f95584eacafe2f2fa2fb4f015";
            StringBuilder sb = new StringBuilder();
            for (String ing : selectedIngredients) {
                sb.append(ing.toLowerCase()).append(",");
            }
            String ingredients = sb.toString().replaceAll(",$", "");

            String findUrl = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" +
                    ingredients + "&number=1&apiKey=" + apiKey;
            HttpURLConnection conn = (HttpURLConnection) new URL(findUrl).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONArray recipes = new JSONArray(response.toString());

            if (recipes.length() > 0) {
                JSONObject recipe = recipes.getJSONObject(0);
                int id = recipe.getInt("id");
                String title = recipe.getString("title");
                String image = recipe.getString("image");

                String detailsUrl = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=" + apiKey;
                HttpURLConnection detailConn = (HttpURLConnection) new URL(detailsUrl).openConnection();
                detailConn.setRequestMethod("GET");

                BufferedReader detailReader = new BufferedReader(new InputStreamReader(detailConn.getInputStream()));
                StringBuilder detailResponse = new StringBuilder();
                while ((line = detailReader.readLine()) != null) {
                    detailResponse.append(line);
                }
                detailReader.close();

                JSONObject fullDetails = new JSONObject(detailResponse.toString());

                List<String> ingredientsList = new ArrayList<>();
                JSONArray extendedIngredients = fullDetails.getJSONArray("extendedIngredients");
                for (int i = 0; i < extendedIngredients.length(); i++) {
                    ingredientsList.add(extendedIngredients.getJSONObject(i).getString("original"));
                }

                String instructions = fullDetails.optString("instructions", "No instructions available.");

                dispose();
                new RecipeResultPage(title, image, ingredientsList, instructions);

            } else {
                JOptionPane.showMessageDialog(this, "No recipe found.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private Map<String, String> createEmojiMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Apple", "🍎"); map.put("Banana", "🍌"); map.put("Strawberry", "🍓");
        map.put("Mango", "🥭"); map.put("Orange", "🍊"); map.put("Pineapple", "🍍");
        map.put("Grapes", "🍇"); map.put("Blueberry", "🍇"); map.put("Watermelon", "🍉");
        map.put("Peach", "🍑"); map.put("Kiwi", "🥝"); map.put("Avocado", "🥑"); map.put("Guava", "🍈");

        map.put("Carrot", "🥕"); map.put("Tomato", "🍅"); map.put("Garlic", "🧄");
        map.put("Corn", "🌽"); map.put("Potato", "🥔"); map.put("Onion", "🧅");
        map.put("Spinach", "🥬"); map.put("Broccoli", "🥦"); map.put("Cucumber", "🥒");
         map.put("Mushroom", "🍄"); map.put("Eggplant", "🍆");
        map.put("Hot Pepper", "🌶️"); map.put("Sweet Potato", "🍠");

        map.put("Milk", "🥛"); map.put("Cheese", "🧀"); map.put("Yogurt", "🥫");
        map.put("Butter", "🧈"); map.put("Cream", "🍨"); map.put("Paneer", "🧀"); map.put("Ghee", "🧈");
        map.put("Bread", "🍞");map.put("Baguette Bread", "🥖");

        map.put("Chicken", "🍗"); map.put("Egg", "🥚"); map.put("Beef", "🥩");
        map.put("Mutton", "🥩"); map.put("Fish", "🐟"); map.put("Turkey", "🦃"); map.put("Prawns", "🦐");
        map.put("Bacon", "🥓");

        map.put("Almonds", "🍙"); map.put("Cashews", "🌰"); map.put("Walnuts", "🌰");
        map.put("Peanuts", "🥜"); map.put("Pistachios", "🥜"); map.put("Hazelnuts", "🌰");
        map.put("ChestNut", "🌰");

        map.put("Water", "💧"); map.put("Salt", "🧂"); map.put("Sugar", "🍬");
        map.put("Honey", "🍯"); map.put("Lemon", "🍋");map.put("Ice", "🧊");

        return map;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecipeGeneratorPage::new);
    }
}
