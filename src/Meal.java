public class Meal extends Item {
    private String mealName;
    private Item[] items;
    private int totalPrice;
    private int totalCalories;

    // Creates an instance of the Meal class with a specified name and items.
    public Meal(String mealName, Item[] items) {
        this.mealName = mealName;
        this.items = items;
        calculateTotalPrice();
        calculateTotalCalories();
    }

    // Calculates the total price of the meal.
    private void calculateTotalPrice() {
        totalPrice = 0;

        for(Item i : items) {
            totalPrice += i.getPrice();
        }
    }

    // Calculate the total calories of the meal.
    private void calculateTotalCalories() {
        totalCalories = 0;

        for(Item i : items) {
            totalCalories += i.getCalories();
        }
    }

    // Returns the name of the meal.
    public String getMealName() {
        return mealName;
    }

    // Returns the items in meal as a string.
    public String getMealCombo() {
        String combo = mealName + " = ";

        for (int i = 0; i < items.length; i++) {
            if (i == 0)
                combo += items[i].getName();
            else
                combo = combo + " + " + items[i].getName();
        }

        return combo;
    }

    // Returns the total price of the meal.
    public int getTotalPrice() {
        return totalPrice;
    }

    // Returns the total calories of the meal.
    public int getTotalCalories() {
        return totalCalories;
    }

    // Returns the items in the meal.
    public Item[] getItems() {
        return items;
    }
}
