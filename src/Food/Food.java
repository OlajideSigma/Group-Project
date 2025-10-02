package Food;



public class Food {
    private String foodItem;
    private String description;

    public Food(String foodItem, String description) {
        this.foodItem = foodItem;
        this.description = description;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public String getDescription() {
        return description;
    }
}