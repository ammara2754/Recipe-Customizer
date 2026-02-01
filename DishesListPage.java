import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DishesListPage extends JFrame {

    private Map<String, String[][]> dishesByCategory;
    private Map<String, String> recipes;

    public DishesListPage(String categoryName) {
        setTitle(categoryName + " Dishes");
        setSize(1000, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background Image
        JLabel bg = new JLabel();
        URL bgUrl = getClass().getResource("/Images/images.jpeg");
        if (bgUrl == null) {
            JOptionPane.showMessageDialog(this, "❌ Background image not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ImageIcon bgIcon = new ImageIcon(bgUrl);
        Image scaled = bgIcon.getImage().getScaledInstance(1000, 650, Image.SCALE_SMOOTH);
        bg.setIcon(new ImageIcon(scaled));
        setContentPane(bg);
        bg.setLayout(null);

        // Title
        JLabel title = new JLabel(categoryName + " Dishes", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(280, 20, 440, 50);
        bg.add(title);

        // Dish Panel
        JPanel dishPanel = new JPanel(new GridLayout(0, 2, 40, 25));
        dishPanel.setBackground(new Color(255, 255, 255, 180));
        dishPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(dishPanel);
        scrollPane.setBounds(70, 90, 860, 470);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bg.add(scrollPane);

        // Back Button
        JButton backBtn = new JButton("\u2190 Back");
        backBtn.setBounds(20, 580, 100, 35);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setBackground(new Color(255, 200, 100));
        backBtn.setForeground(Color.DARK_GRAY);
        backBtn.setFocusPainted(false);
        bg.add(backBtn);
        backBtn.addActionListener(e -> dispose());

        // Dishes
        dishesByCategory = new HashMap<>();
        dishesByCategory.put("Italian", new String[][]{
                {"Pizza", "Cheesy pizza with toppings.", "/Images/Pizza.jpg"},
                {"Pasta", "Creamy pasta with herbs.", "/Images/Pasta.jpg"},
                {"Lasagna", "Layered lasagna.", "/Images/Lasagna.jpg"},
                {"Risotto", "Rice with broth and cheese.", "/Images/Risotto.jpg"}
        });
        dishesByCategory.put("Chinese", new String[][]{
                {"Chow Mein", "Stir-fried noodles.", "/Images/chowmein.jpg"},
                {"Sweet & Sour Chicken", "Tangy chicken.", "/Images/Chicken.jpg"},
                {"Spring Rolls", "Veggie rolls.", "/Images/springrolls.jpg"},
                {"Kung Pao Chicken", "Spicy chicken stir-fry.", "/Images/kungpao.jpg"}
        });
        dishesByCategory.put("Pakistani", new String[][]{
                {"Biryani", "Spiced rice dish.", "/Images/biryani.jpg"},
                {"Nihari", "Beef stew.", "/Images/nihari.jpg"},
                {"Haleem", "Wheat and meat dish.", "/Images/haleem.jpg"},
                {"Karahi", "Tomato-based curry.", "/Images/karahi.jpg"}
        });
        dishesByCategory.put("Mexican", new String[][]{
                {"Tacos", "Corn shells with filling.", "/Images/tacos.jpeg"},
                {"Burritos", "Tortilla wrap.", "/Images/Burritos.jpeg"},
                {"Nachos", "Chips with cheese.", "/Images/Nachos.jpeg"},
                {"Quesadilla", "Grilled cheesy tortilla.", "/Images/quesadilla.jpg"}
        });
        dishesByCategory.put("American", new String[][]{
                {"Burger", "Juicy grilled beef patty.", "/Images/burger.jpg"},
                {"Fried Chicken", "Crispy southern style.", "/Images/FriedChicken.jpeg"},
                {"Hot Dog", "Sausage in bun.", "/Images/Hotdog.jpeg"},
                {"Mac & Cheese", "Creamy cheesy pasta.", "/Images/macncheese.jpg"}
        });
        dishesByCategory.put("Vegetarian", new String[][]{
                {"Grilled Veggie Sandwich", "Toasted bread with grilled veggies.", "/Images/veggiesandwich.jpg"},
                {"Paneer Tikka", "Spicy Indian cottage cheese.", "/Images/paneertikka.jpg"},
                {"Veggie Stir Fry", "Colorful stir-fried vegetables.", "/Images/veggiestirfry.jpg"},
                {"Mushroom Soup", "Creamy mushroom soup.", "/Images/MushRoomSoup.jpeg"}
        });

        // Recipes
        recipes = new HashMap<>();
        recipes.put("Biryani",
                "🍲 Ingredients (4‑5 servings):\n" +
                        "- 2 cups Basmati rice\n- 500 g chicken, cut pieces\n- 1 cup yogurt\n- 2 fried onions\n- 2 tbsp ginger‑garlic paste\n- 2 tsp red chili powder\n- 1 tsp turmeric\n- 1 tsp garam masala\n- Salt to taste\n- Saffron + ¼ cup milk\n- ¼ cup fresh mint & coriander\n\n" +
                        "⏱️  Cooking Time: 1h 20m (Marinate + Dum)\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Marinate chicken with yogurt, spices, ginger‑garlic paste & onions—30m.\n" +
                        "2. Soak rice for 30m; par‑boil (70%)\n" +
                        "3. Layer rice → chicken → rice in pot.\n" +
                        "4. Pour saffron milk, sprinkle herbs.\n" +
                        "5. Seal and cook on low heat (dum) 30m.\n\n🍽️ Serve with raita."
        );

        recipes.put("Nihari",
                "🍲 Ingredients (4 servings):\n" +
                        "- 1 kg beef shank\n- 2 onions, sliced\n- 2 tbsp nihari masala\n- 1 tsp ginger‑garlic paste\n- ½ cup oil\n- Salt to taste\n\n⏱️ Cooking Time: 6–8h (slow cook)\n\n👨‍🍳 Method:\n" +
                        "1. Fry onions until dark brown, remove half.\n" +
                        "2. In pot, layer meat + masala + ginger‑garlic + fried onions.\n" +
                        "3. Add water; simmer low for 5–6h (or slow cooker).\n" +
                        "4. Thicken with wheat flour paste; cook 15m.\n\n🍽️ Garnish & serve with naan."
        );

        recipes.put("Haleem",
                "🍲 Ingredients (6–8 servings):\n" +
                        "- 500 g boneless beef or chicken\n- 1 cup wheat + barley (cracked)\n- ½ cup lentils mix (masoor, chana, moong)\n" +
                        "- 2 onions, sliced\n- 2 tbsp ginger‑garlic paste\n- 2 tbsp haleem masala\n- ½ cup oil/ghee\n- Salt to taste\n\n" +
                        "⏱️ Cooking Time: 5–6h (soaking + slow cook)\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Soak grains/lentils overnight. Boil separately.\n" +
                        "2. Cook meat with masala, onions & ginger‑garlic paste till tender.\n" +
                        "3. Shred meat, mix with grains.\n" +
                        "4. Blend & cook slowly for 1–2h. Stir often.\n\n🍽️ Garnish with fried onions, lemon, mint, green chilies."
        );

        recipes.put("Karahi",
                "🍲 Ingredients (4 servings):\n" +
                        "- 500 g chicken or mutton\n- 3 tomatoes, chopped\n- 2 tbsp ginger‑garlic paste\n" +
                        "- 4–5 green chilies\n- ½ cup oil\n- 1 tsp red chili flakes\n- 1 tsp garam masala\n- Salt to taste\n\n" +
                        "⏱️ Cooking Time: 40–45m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Heat oil, add meat & ginger‑garlic. Fry 5–6m.\n" +
                        "2. Add tomatoes & spices. Cook until oil separates.\n" +
                        "3. Add green chilies, simmer till meat is tender.\n" +
                        "4. Sprinkle garam masala before serving.\n\n🍽️ Serve hot with naan or paratha."
        );



        // 🍝 Italian Recipes:

        recipes.put("Pizza",
                "🍕 Ingredients (2 pizzas):\n" +
                        "- 2 cups flour\n- 1 tsp yeast + 1 tsp sugar\n- ½ cup warm water\n- ½ cup tomato sauce\n- 1 cup mozzarella cheese\n- Toppings of choice\n\n⏱️ Cooking Time: 1h 15m (incl. resting)\n\n👨‍🍳 Method:\n" +
                        "1. Mix flour, yeast, sugar, salt; knead + rise 45m.\n" +
                        "2. Roll dough, add sauce, toppings & cheese.\n" +
                        "3. Bake 15‑20m at 220 °C.\n\n🍽️ Slice & serve warm."
        );

        recipes.put("Pasta",
                "🍝 Ingredients (4 servings):\n" +
                        "- 300 g pasta\n- 2 cloves garlic, chopped\n- 1 tbsp olive oil\n- 1 cup heavy cream\n- ½ cup parmesan cheese\n- Salt, pepper, parsley\n\n⏱️ Cooking Time: 25m\n\n👨‍🍳 Method:\n" +
                        "1. Boil pasta; drain.\n" +
                        "2. Sauté garlic in oil; add cream & cheese.\n" +
                        "3. Toss pasta, season, serve with parsley."
        );

// Lasagna and Risotto next...
        recipes.put("Lasagna",
                "🍲 Ingredients (6 servings):\n" +
                        "- 9 lasagna sheets\n- 500 g ground beef\n- 2 cups tomato sauce\n- 1 onion, chopped\n" +
                        "- 2 cups béchamel sauce\n- 1½ cups mozzarella/cheddar\n- 2 tbsp olive oil\n- Salt, pepper, oregano\n\n" +
                        "⏱️ Cooking Time: 1h 30m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Sauté onion, add beef, cook till brown.\n" +
                        "2. Add tomato sauce, spices; simmer 20m.\n" +
                        "3. Layer: sheets → meat → béchamel → cheese (repeat).\n" +
                        "4. Bake at 180 °C for 30–40m.\n\n🍽️ Let it rest, then slice & enjoy!"
        );

        recipes.put("Risotto",
                "🍚 Ingredients (4 servings):\n" +
                        "- 1½ cups Arborio rice\n- 1 onion, chopped\n- 2 tbsp olive oil or butter\n" +
                        "- ½ cup white wine (optional)\n- 4 cups chicken/veg broth (warm)\n- ½ cup parmesan cheese\n- Salt & pepper\n\n" +
                        "⏱️ Cooking Time: 35–40m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Sauté onion in oil, add rice; stir till coated.\n" +
                        "2. Add wine (optional), stir till absorbed.\n" +
                        "3. Add broth gradually, stirring constantly.\n" +
                        "4. When creamy & tender, stir in cheese.\n\n🍽️ Serve hot with herbs or grilled veggies."
        );
        recipes.put("Chow Mein",
                "🍜 Ingredients (4 servings):\n" +
                        "- 300 g egg noodles\n- 1 cup shredded cabbage\n- 1 carrot, julienned\n- 1 bell pepper, sliced\n" +
                        "- 2 tbsp soy sauce\n- 1 tbsp chili sauce\n- 1 tsp vinegar\n- 2 tbsp oil\n- Salt & pepper\n\n" +
                        "⏱️ Cooking Time: 30m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Boil noodles, rinse & toss in oil.\n" +
                        "2. Stir-fry vegetables in hot oil.\n" +
                        "3. Add sauces, noodles; toss well.\n\n🍽️ Serve hot with chili sauce."
        );

        recipes.put("Sweet & Sour Chicken",
                "🍗 Ingredients (4 servings):\n" +
                        "- 500 g boneless chicken (cubed)\n- 1 egg + 2 tbsp cornflour\n- 1 cup bell peppers, diced\n" +
                        "- ½ cup pineapple chunks\n- 2 tbsp ketchup\n- 1 tbsp soy sauce\n- 1 tbsp vinegar\n- 1 tsp sugar\n- Salt to taste\n\n" +
                        "⏱️ Cooking Time: 40m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Coat chicken with egg + cornflour; fry till golden.\n" +
                        "2. Stir-fry veggies, add sauces, pineapple, sugar.\n" +
                        "3. Add fried chicken; toss till coated.\n\n🍽️ Serve with steamed rice or noodles."
        );
        recipes.put("Spring Rolls",
                "🥟 Ingredients (10 rolls):\n" +
                        "- Spring roll wrappers (10)\n- 1 cup shredded cabbage\n- ½ cup grated carrots\n- ½ cup bean sprouts\n" +
                        "- 2 tbsp soy sauce\n- 1 tsp vinegar\n- Salt & pepper\n- Oil for frying\n\n" +
                        "⏱️ Cooking Time: 30–35m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Stir-fry veggies in little oil + sauces.\n" +
                        "2. Let filling cool. Place on wrapper, roll tightly.\n" +
                        "3. Seal edges with flour paste. Deep fry till golden.\n\n🍽️ Serve with sweet chili sauce."
        );

        recipes.put("Kung Pao Chicken",
                "🌶️ Ingredients (4 servings):\n" +
                        "- 500 g boneless chicken (cubes)\n- 1 bell pepper, diced\n- ½ cup roasted peanuts\n- 2–3 dried red chilies\n" +
                        "- 2 tbsp soy sauce\n- 1 tbsp vinegar\n- 1 tbsp hoisin or oyster sauce\n- 1 tsp cornflour (slurry)\n- 2 tbsp oil\n\n" +
                        "⏱️ Cooking Time: 30m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Stir-fry chicken in oil; remove.\n" +
                        "2. Stir-fry chilies, bell peppers; add chicken back.\n" +
                        "3. Add sauces + slurry + peanuts; cook till thick.\n\n🍽️ Serve hot with rice."
        );
//Mexicn
        recipes.put("Tacos",
                "🌮 Ingredients (4 servings):\n" +
                        "- 8 corn taco shells\n- 300 g ground beef/chicken\n- 1 onion, chopped\n- 1 tomato, diced\n" +
                        "- Lettuce, shredded\n- ½ cup cheddar cheese\n- 1 tbsp taco seasoning\n- Salsa or sour cream\n\n" +
                        "⏱️ Cooking Time: 30m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Cook meat with onions & taco seasoning.\n" +
                        "2. Warm taco shells; fill with meat, veggies, cheese.\n" +
                        "3. Top with salsa or sour cream.\n\n🍽️ Serve immediately."
        );

        recipes.put("Burritos",
                "🌯 Ingredients (4 servings):\n" +
                        "- 4 large tortillas\n- 1½ cups cooked rice\n- 1 cup beans (black or refried)\n" +
                        "- 300 g grilled chicken/beef\n- Salsa, cheese, sour cream\n- Lettuce (optional)\n\n" +
                        "⏱️ Cooking Time: 35m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Warm tortillas; layer rice, beans, meat, toppings.\n" +
                        "2. Fold sides, roll tightly into a burrito.\n" +
                        "3. Toast on pan or grill (optional).\n\n🍽️ Enjoy warm with dipping sauce."
        );
        recipes.put("Nachos",
                "🧀 Ingredients (4 servings):\n" +
                        "- 200 g tortilla chips\n- 1 cup cheddar cheese (grated)\n- ½ cup black beans (cooked)\n" +
                        "- ¼ cup jalapeños\n- ½ cup salsa\n- Sour cream & guacamole (for topping)\n\n" +
                        "⏱️ Cooking Time: 15–20m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Arrange chips on baking tray.\n" +
                        "2. Add beans, jalapeños, cheese.\n" +
                        "3. Bake at 180 °C until cheese melts.\n" +
                        "4. Top with salsa, sour cream, guacamole.\n\n🍽️ Serve hot & crispy."
        );

        recipes.put("Quesadilla",
                "🫓 Ingredients (2 quesadillas):\n" +
                        "- 4 flour tortillas\n- 1 cup mozzarella/cheddar cheese\n- 1 cup cooked chicken or veggies\n" +
                        "- 1 tsp chili flakes (optional)\n- Butter or oil for toasting\n\n" +
                        "⏱️ Cooking Time: 15–20m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Place cheese & filling on one tortilla.\n" +
                        "2. Cover with another tortilla; press gently.\n" +
                        "3. Toast on pan with butter until golden on both sides.\n\n🍽️ Slice & serve with salsa or sour cream."
        );
        //American
        recipes.put("Nachos",
                "🧀 Ingredients (4 servings):\n" +
                        "- 200 g tortilla chips\n- 1 cup cheddar cheese (grated)\n- ½ cup black beans (cooked)\n" +
                        "- ¼ cup jalapeños\n- ½ cup salsa\n- Sour cream & guacamole (for topping)\n\n" +
                        "⏱️ Cooking Time: 15–20m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Arrange chips on baking tray.\n" +
                        "2. Add beans, jalapeños, cheese.\n" +
                        "3. Bake at 180 °C until cheese melts.\n" +
                        "4. Top with salsa, sour cream, guacamole.\n\n🍽️ Serve hot & crispy."
        );

        recipes.put("Quesadilla",
                "🫓 Ingredients (2 quesadillas):\n" +
                        "- 4 flour tortillas\n- 1 cup mozzarella/cheddar cheese\n- 1 cup cooked chicken or veggies\n" +
                        "- 1 tsp chili flakes (optional)\n- Butter or oil for toasting\n\n" +
                        "⏱️ Cooking Time: 15–20m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Place cheese & filling on one tortilla.\n" +
                        "2. Cover with another tortilla; press gently.\n" +
                        "3. Toast on pan with butter until golden on both sides.\n\n🍽️ Slice & serve with salsa or sour cream."
        );
        recipes.put("Hot Dog",
                "🌭 Ingredients (4 servings):\n" +
                        "- 4 hot dog buns\n- 4 sausages (beef/chicken)\n- Mustard, ketchup\n- Onion (chopped)\n" +
                        "- Pickles or relish (optional)\n\n" +
                        "⏱️ Cooking Time: 15–20m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Boil or grill sausages till cooked.\n" +
                        "2. Warm buns; place sausage inside.\n" +
                        "3. Add mustard, ketchup, onion & toppings.\n\n🍽️ Serve immediately."
        );

        recipes.put("Mac & Cheese",
                "🧀 Ingredients (4 servings):\n" +
                        "- 2 cups macaroni pasta\n- 2 tbsp butter\n- 2 tbsp flour\n- 2 cups milk\n" +
                        "- 1½ cups shredded cheddar\n- Salt, pepper\n\n" +
                        "⏱️ Cooking Time: 30m\n\n" +
                        "👨‍🍳 Method:\n" +
                        "1. Boil pasta & drain.\n" +
                        "2. Make roux: melt butter, add flour, cook 1m.\n" +
                        "3. Add milk, stir till thick. Add cheese.\n" +
                        "4. Mix with pasta; season & serve warm.\n\n🍽️ Creamy comfort food!"
        );

        // Load and show cards
        String[][] dishes = dishesByCategory.getOrDefault(categoryName, new String[][]{});
        for (String[] dish : dishes) {
            String name = dish[0];
            String desc = dish[1];
            String imgPath = dish[2];

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            card.setPreferredSize(new Dimension(240, 160));
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JLabel imgLabel = new JLabel();
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imgLabel.setPreferredSize(new Dimension(160, 80));
            URL imgUrl = getClass().getResource(imgPath);
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(img));
            } else {
                imgLabel.setText("Image Not Found");
                imgLabel.setForeground(Color.RED);
            }

            JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
            nameLabel.setFont(new Font("Verdana", Font.BOLD, 14));
            nameLabel.setForeground(new Color(80, 40, 20));

            JLabel descLabel = new JLabel("<html><center>" + desc + "</center></html>", SwingConstants.CENTER);
            descLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            descLabel.setForeground(Color.DARK_GRAY);

            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.setOpaque(false);
            textPanel.add(nameLabel);
            textPanel.add(descLabel);

            card.add(imgLabel, BorderLayout.CENTER);
            card.add(textPanel, BorderLayout.SOUTH);

            card.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String recipe = recipes.getOrDefault(name, "Recipe not available yet.");
                    new DishDetailPage(name, desc, imgPath, recipe).setVisible(true);
                }

                public void mouseEntered(MouseEvent e) {
                    card.setBackground(new Color(255, 248, 220));
                    card.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2));
                }

                public void mouseExited(MouseEvent e) {
                    card.setBackground(Color.WHITE);
                    card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                }
            });

            dishPanel.add(card);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DishesListPage("Italian").setVisible(true));
    }
}
