import java.util.ArrayList;

public class Item {
    private String name;
    private int calories;
    private int price;
    private int quantity;

    // Creates an instance of the Item class with specified name, price, and quantity.
    public Item(String name, int calories, int price, int quantity) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.quantity = quantity;
    }

    // Creates an instance of the Item class.
    public Item() {}

    // Converts the details of an item into an ArrayList of strings.
    public ArrayList<String> detailsToString() {
        ArrayList<String> details = new ArrayList<String>();
        details.add(String.valueOf(calories));
        details.add(name);
        details.add(String.valueOf(price));
        details.add(String.valueOf(quantity));

        return details;
    }

    // Returns the price of the item.
    public int getPrice() {
        return price;
    }

    // Returns the quantity of the item.
    public int getQuantity() {
        return quantity;
    }

    // Returns the calories of the item.
    public int getCalories() {
        return calories;
    }

    // Returns the name of the item.
    public String getName() {
        return name;
    }

    // Decreases the item's quantity by one.
    public void removeOne() {
        quantity--;
    }

    // Adds a specified number to the item's quantity.
    public void addQuantity(int quantity) {
        this.quantity += quantity;
      }

    // Sets the price of the item to a specified number.
    public void setPrice(int price) {
        this.price = price;
    }
}
