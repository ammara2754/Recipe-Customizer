import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
class RecipeApiExample {

    public static void main(String[] args) {
        try {
            String apiKey = "d1bc297f95584eacafe2f2fa2fb4f015";

            // Ingredients (comma-separated, e.g., chicken,tomato,onion)
            String ingredientsRaw = "chicken,tomato,onion"; // ❗ space na do yahan, warna encode karo
            String ingredients = URLEncoder.encode(ingredientsRaw, StandardCharsets.UTF_8);

            // API URL
            String apiUrl = "https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                    + ingredients + "&number=5&apiKey=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("❌ Error: " + responseCode + " - " + connection.getResponseMessage());
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            System.out.println("✅ Response JSON:\n" + responseContent.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
