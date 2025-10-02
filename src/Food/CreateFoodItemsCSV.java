package Food;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFoodItemsCSV {
    public static void main(String[] args) {
        // Define the file path
        String filePath = "food_items.csv";

        // Data to write to the CSV file
        String[][] data = {
            {"Name", "Price", "Stock", "ImagePath", "Description"}, // Header row
            
           
            
            { "Vegetable Soup", "2.00", "10", "src/Food/Screenshot 2025-03-13 144813 - Copy.png", "A hearty soup made with fresh vegetables." },
            { "Avocado Toast", "4.50", "5", "src/Food/Screenshot 2025-03-22 000200 - Copy.png", "Smashed avocado on whole-grain toast." },
            { "Eggplant Parmesan", "6.75", "8", "src/Food/Screenshot 2025-03-13 144836 - Copy.png", "Baked eggplant with marinara and cheese." },
            { "Margherita Pizza", "8.50", "7", "src/Food/Screenshot 2025-03-22 202940 - Copy.png", "Classic pizza with tomato, mozzarella, and basil." },
            { "Caesar Salad", "5.75", "12", "src/Food/Screenshot 2025-03-22 203010 - Copy.png", "Crisp romaine lettuce with croutons and Caesar dressing." },
            { "Greek Yogurt Parfait", "3.25", "15", "src/Food/Screenshot 2025-03-13 144847 - Copy.png", "Layered yogurt with granola and fresh fruits." },
            { "Pasta Carbonara", "7.50", "6", "src/Food/Screenshot 2025-03-22 203150 - Copy.png", "Creamy pasta with eggs, cheese, and pancetta." },
            { "Cheesecake", "4.00", "10", "src/Food/Screenshot 2025-03-22 203214 - Copy.png", "Rich and creamy classic cheesecake." },
            { "Tiramisu", "5.00", "8", "src/Food/Screenshot 2025-03-22 203237 - Copy.png", "Italian dessert with coffee-soaked ladyfingers and mascarpone." },
            { "Fruit Salad", "3.50", "20", "src/Food/Screenshot 2025-03-22 203301 - Copy.png", "A refreshing mix of seasonal fruits." },
            { "Grilled Chicken Salad", "8.50", "10", "src/Food/Screenshot 2025-03-22 203322 - Copy.png", "Grilled chicken breast on a bed of fresh greens." },
            { "Baked Salmon", "12.00", "8", "src/Food/Screenshot 2025-03-22 000239 - Copy.png", "Oven-baked salmon with herbs and lemon." },
            { "Chicken Noodle Casserole", "9.00", "12", "src/Food/Screenshot 2025-03-22 203552 - Copy.png", "Comforting casserole with chicken and noodles." },
            { "Quinoa Salad", "7.50", "10", "src/Food/Screenshot 2025-03-22 000356 - Copy.png", "Healthy salad with quinoa, vegetables, and vinaigrette." },
            { "Turkey Sandwich", "6.50", "10", "src/Food/Screenshot 2025-03-13 144910 - Copy.png", "Sliced turkey with lettuce, tomato, and mayo on whole-grain bread." },
            { "Minestrone Soup", "5.50", "15", "src/Food/Screenshot 2025-03-13 144901 - Copy.png", "Italian vegetable soup with pasta and beans." },
            { "Baked Cod Fish", "11.00", "8", "src/Food/Screenshot 2025-03-22 203629 - Copy.png", "Oven-baked cod with a light herb crust." },
            { "Vegetarian Wrap", "7.00", "10", "src/Food/Screenshot 2025-03-22 000319 - Copy.png", "Wrap filled with fresh vegetables and hummus." },
            { "Grilled Vegetable Platter", "9.50", "10", "src/Food/Screenshot 2025-03-22 000354 - Copy.png", "Assorted grilled vegetables with a balsamic glaze." },
            { "Chicken Caesar Salad", "8.50", "10", "src/Food/Screenshot 2025-03-22 0 - Copy.png", "Classic Caesar salad with grilled chicken." },
            { "Tomato Basil Panini", "6.50", "10", "src/Food/Screenshot 2025-03-13 144827 - Copy.png", "Grilled panini with tomato, basil, and mozzarella." },
            // { "Lentil Soup", "5.00", "15", "src/food/Screenshot 2025-03-13 144813.png", "Hearty soup made with lentils and vegetables." },
            // { " ", "10.00", "10", "src/food/pasta_primavera.png", "Pasta with fresh spring vegetables in a light sauce." },
            // { "Roast Beef Wrap", "8.00", "10", "src/food/roast_beef_wrap.png", "Wrap filled with roast beef, lettuce, and horseradish sauce." },
            // { "Steamed Vegetable Dumplings", "7.50", "10", "src/food/steamed_vegetable_dumplings.png", "Steamed dumplings filled with vegetables." },
            { "Salmon Teriyaki Bowl", "12.50", "8", "src/Food/Screenshot 2025-03-22 0003554 - Copy.png", "Grilled salmon with teriyaki sauce over rice." },
            { "Egg Salad Sandwich", "5.50", "10", "src/Food/Screenshot 2025-03-22 00043 - Copy.png", "Creamy egg salad on whole-grain bread." },
            // { "Mushroom Barley Soup", "5.50", "15", "src/food/mushroom_barley_soup.png", "Hearty soup with mushrooms and barley." },
            // { "Turkey Burger", "9.00", "10", "src/food/turkey_burger.png", "Juicy turkey burger with lettuce and tomato." },
            { "Spinach and Feta Wrap", "7.50", "10", "src/Food/Screenshot 2025-03-22 000354 - Copy.png", "Wrap filled with spinach, feta, and olives." },
            // { "Chicken Rice Bowl", "10.00", "10", "src/food/chicken_rice_bowl.png", "Grilled chicken over steamed rice with vegetables." },
            // { "Veggie Burger", "8.00", "10", "src/food/veggie_burger.png", "Plant-based burger with fresh toppings." },
            // { "Caesar Wrap", "7.50", "10", "src/food/caesar_wrap.png", "Wrap with Caesar salad ingredients." },
            // { "Caprese Salad", "8.00", "10", "src/food/caprese_salad.png", "Fresh mozzarella, tomatoes, and basil with balsamic glaze." },
            // { "Hummus and Veggie Plate", "7.00", "10", "src/food/hummus_veggie_plate.png", "Hummus served with fresh vegetables." },
            // { "Cheese and Turkey Panini", "7.50", "10", "src/food/cheese_turkey_panini.png", "Grilled panini with turkey and cheese." },
            // { "Chicken and Rice Soup", "6.50", "15", "src/food/chicken_rice_soup.png", "Comforting soup with chicken and rice." },
            // { "Vegan Lentil Burger", "8.50", "10", "src/food/vegan_lentil_burger.png", "Plant-based burger made with lentils." },
            { "Shrimp and Avocado Salad", "11.00", "8", "src/Food/Screenshot 2025-03-22  - Copy.png", "Salad with shrimp, avocado, and citrus dressing." },
            // { "Chicken Vegetable Stir-Fry", "10.50", "10", "src/food/chicken_vegetable_stir_fry.png", "Stir-fried chicken with mixed vegetables." },
            // { "Vegetable Lasagna", "9.50", "10", "src/food/vegetable_lasagna.png", "Layered lasagna with vegetables and cheese." },
            // { "Fruit Smoothie Bowl", "6.50", "10", "src/food/fruit_smoothie_bowl.png", "Smoothie bowl topped with fresh fruits and granola." },
            // { "Mediterranean Wrap", "8.00", "10", "src/food/mediterranean_wrap.png", "Wrap with Mediterranean flavors like hummus and olives." },
            // { "Turkey Chili", "7.50", "15", "src/food/turkey_chili.png", "Hearty chili made with ground turkey and beans." },
            // { "Salmon Caesar Wrap", "11.50", "8", "src/food/salmon_caesar_wrap.png", "Wrap with grilled salmon and Caesar salad ingredients." },
            // { "Eggplant Parmesan", "9.00", "10", "src/food/eggplant_parmesan.png", "Breaded eggplant with marinara and cheese." },
            // { "Chicken Pesto Pasta", "10.00", "10", "src/food/chicken_pesto_pasta.png", "Pasta with chicken and basil pesto sauce." },
            // { "Veggie Sushi Roll", "8.50", "10", "src/food/veggie_sushi_roll.png", "Sushi roll filled with fresh vegetables." },
            // { "Lemon Herb Tilapia", "11.00", "8", "src/food/lemon_herb_tilapia.png", "Tilapia fillet with lemon and herbs." },
            // { "Black Bean and Corn Salad", "7.00", "10", "src/food/black_bean_corn_salad.png", "Salad with black beans, corn, and lime dressing." },
            // { "Chicken Avocado Wrap", "8.50", "10", "src/food/chicken_avocado_wrap.png", "Wrap with chicken, avocado, and fresh greens." },
            // { "Quinoa Stuffed Peppers", "9.00", "10", "src/food/quinoa_stuffed_peppers.png", "Bell peppers stuffed with quinoa and vegetables." },
            // { "Turkey Meatball Sub", "9.50", "10", "src/food/turkey_meatball_sub.png", "Sub sandwich with turkey meatballs and marinara." },
            // { "Vegetarian Burrito Bowl", "8.50", "10", "src/food/vegetarian_burrito_bowl.png", "Burrito bowl with rice, beans, and vegetables." },
            // { "Sweet Potato and Chickpea Curry", "9.00", "10", "src/food/sweet_potato_chickpea_curry.png", "Curry with sweet potatoes and chickpeas." },
            // { "Cauliflower Fried Rice", "8.00", "10", "src/food/cauliflower_fried_rice.png", "Low-carb fried rice made with cauliflower." },
            // { "Caprese Wrap", "7.50", "10", "src/food/caprese_wrap.png", "Wrap with Caprese salad ingredients." },
            // { "Chicken Broccoli Alfredo", "10.50", "10", "src/food/chicken_broccoli_alfredo.png", "Creamy Alfredo pasta with chicken and broccoli." },
            { "Spicy Tofu Stir-Fry", "9.50", "10", "src/Food/Screenshot 2025-03-13 144926 - Copy.png", "Stir-fried tofu with spicy sauce and vegetables." },
            // { "Greek Chicken Salad", "8.50", "10", "src/food/greek_chicken_salad.png", "Salad with grilled chicken, olives, and feta." },
            // { "Vegetarian Chili", "7.50", "15", "src/food/vegetarian_chili.png", "Hearty chili made with beans and vegetables." },
            // { "Lemon Dill Quinoa", "8.00", "10", "src/food/lemon_dill_quinoa.png", "Quinoa flavored with lemon and dill." },
            // { "Turkey Club Sandwich", "7.50", "10", "src/food/turkey_club_sandwich.png", "Classic club sandwich with turkey, bacon, and lettuce." },
            // { "Avocado Toast", "6.00", "10", "src/food/Screenshot 2025-03-13 144827.png", "Smashed avocado on whole-grain toast." },
            // { "Chicken Teriyaki Bowl", "10.50", "10", "src/food/chicken_teriyaki_bowl.png", "Grilled chicken with teriyaki sauce over rice." },
            { "Sweet and Sour Tofu", "9.00", "10", "src/Food/Screenshot 2025-03-13 144926 - Copy.png", "Tofu stir-fried in sweet and sour sauce." },


        };

        // Write data to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                // Join the row elements with a comma separator
                String line = String.join(",", row);
                writer.write(line);
                writer.newLine(); // Move to the next line
            }
            System.out.println("CSV file created successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}