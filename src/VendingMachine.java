import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class VendingMachine {
    protected ArrayList<Item> items;
    protected Money money;
    protected Calendar lastRestock;
    protected ArrayList<Item> soldItems;
    protected ArrayList<Integer> startingInventory;

    // Constructor - set item and money defaults, set last restock to current date
    public VendingMachine() {
        startingInventory = new ArrayList<Integer>();
        setDefaultItems();
        money = new Money("def");
        lastRestock = Calendar.getInstance();
        soldItems = new ArrayList<Item>();
    }

  // Default items  
  private void setDefaultItems() {
        ArrayList<Item> newItems = new ArrayList<Item>();

        String[] names = { "Fried Rice", "Garlic Rice", "Java Rice", "White Rice", "Brown Rice",
                        "Curry", "Natto", "BBQ Chicken", "Beef", "Pork",
                        "Carrot", "Nori", "Chili Peppers", "Egg", "Green Onion" };

        int[] calories = { 228, 476, 234, 206, 216,
                        124, 100, 226, 276, 282,
                        25, 1, 30, 97, 2 };

        int[] price = { 30, 30, 30, 25, 25,
                        150, 150, 130, 125, 100,
                        25, 20, 15, 10, 10 };

        for (int i = 0; i < 15; i++) {
            Item cur = new Item(names[i], calories[i], price[i], 10);
            newItems.add(cur);
            startingInventory.add(10);
        }

        items = newItems;
    }

  // Return formatted list of each item detail  
  public ArrayList<String> getItemDetails() {
        ArrayList<String> itemDetails = new ArrayList<String>();
        itemDetails.add("Slot");
        itemDetails.add("Calories");
        itemDetails.add("Item Name");
        itemDetails.add("Price");
        itemDetails.add("Quantity");
        
        for (Item i : items) {
            itemDetails.addAll(i.detailsToString());
        }

        return itemDetails;
    }

    // Return list of item prices
    public ArrayList<Integer> getPrices() {
        ArrayList<Integer> prices = new ArrayList<Integer>();

        for (Item item : items) {
            prices.add(item.getPrice());
        }

        return prices;
    }

    // Return list of item quantities
    public ArrayList<Integer> getQuantities() {
        ArrayList<Integer> quantities = new ArrayList<Integer>();

        for (Item item : items) {
            quantities.add(item.getQuantity());
        }

        return quantities;
    }

    // Add money to VM
    public void addMoney(Money m)  {
        for (int i = 0; i < m.getC1(); i++) {
            money.addC1();
        }

        for (int i = 0; i < m.getC5(); i++) {
            money.addC5();
        }

        for (int i = 0; i < m.getC10(); i++) {
            money.addC10();
        }

        for (int i = 0; i < m.getC20(); i++) {
            money.addC20();
        }

        for (int i = 0; i < m.getC50(); i++) {
            money.addC50();
        }

        for (int i = 0; i < m.getC100(); i++) {
            money.addC100();
        }

        for (int i = 0; i < m.getC200(); i++) {
            money.addC200();
        }

        for (int i = 0; i < m.getC500(); i++) {
            money.addC500();
        }

        for (int i = 0; i < m.getC1000(); i++) {
            money.addC1000();
        }
    }

    // Get change depending on VM money
    public Money getChange(int amount) {
        Money change = new Money();
        int c1, c5, c10, c20, c50, c100, c200, c500, c1000;
    
        c1000 = amount / 1000;
        if (c1000 > money.getC1000()) {
          c1000 = money.getC1000();
        }
        amount -= c1000 * 1000;
    
        c500 = amount / 500;
        if (c500 > money.getC500()) {
          c500 = money.getC500();
        }
        amount -= c500 * 500;
    
        c200 = amount / 200;
        if (c200 > money.getC200()) {
          c200 = money.getC200();
        }
        amount -= c200 * 200;
    
        c100 = amount / 100;
        if (c100 > money.getC100()) {
          c100 = money.getC100();
        }
        amount -= c100 * 100;
    
        c50 = amount / 50;
        if (c50 > money.getC50()) {
          c50 = money.getC50();
        }
        amount -= c50 * 50;
    
        c20 = amount / 20;
        if (c20 > money.getC20()) {
          c20 = money.getC20();
        }
        amount -= c20 * 20;
    
        c10 = amount / 10;
        if (c10 > money.getC10()) {
          c10 = money.getC10();
        }
        amount -= c10 * 10;
    
        c5 = amount / 5;
        if (c5 > money.getC5()) {
          c5 = money.getC5();
        }
        amount -= c5 * 5;
    
        c1 = amount / 1;
        // If VM cannot product enough change
        if (c1 > money.getC1()) {
          return null;
        }
        amount -= c1 * 1;

        for (int i = 0; i < c1000; i++) {
            change.addC1000();
            money.removeC1000();
        }
    
        for (int i = 0; i < c500; i++) {
            change.addC500();
            money.removeC500();
        }

        for (int i = 0; i < c200; i++) {
            change.addC200();
            money.removeC200();
        }

        for (int i = 0; i < c100; i++) {
            change.addC100();
            money.removeC100();
        }

        for (int i = 0; i < c50; i++) {
            change.addC50();
            money.removeC50();
        }

        for (int i = 0; i < c20; i++) {
            change.addC20();
            money.removeC20();
        }

        for (int i = 0; i < c10; i++) {
            change.addC10();
            money.removeC10();
        }

        for (int i = 0; i < c5; i++) {
            change.addC5();
            money.removeC5();
        }

        for (int i = 0; i < c1; i++) {
            change.addC1();
            money.removeC1();
        }
    
        return change;
      }

    // Removes 1 item from slot
    public void removeItem(int slot) {
        if (slot < 20)
            items.get(slot).removeOne();
    }

    // Adds item to sold items
    public void addSoldItem(int slot) {
        if (slot < 20) {
            Item item = items.get(slot);

            int i = this.findItem(item.getName(), this.soldItems);

            if (i >= 0) {
            soldItems.get(i).addQuantity(1);
            } else {
            soldItems.add(new Item(item.getName(), item.getCalories(), item.getPrice(), 1));
            }
        }
        
    }

    // Find item slot index
    public int findItem(String name, ArrayList<Item> items) {
        int i = 0;
        for (Item item : items) {
          if (item.getName().equals(name)) {
            return i;
          }
          i++;
        }
    
        return -1;
    }

    // Get item name from slot
    public String getItemName(int slot) {
        return items.get(slot).getName();
    }

    // Get item calories from slot
    public int getItemCalories(int slot) {
        return items.get(slot).getCalories();
    }

  // Get item quantity from slot
    public int getItemQuantity(int slot) {
        return items.get(slot).getQuantity();
    }

    // Get item price from slot
    public int getItemPrice(int slot) {
        return items.get(slot).getPrice();
    }

    // Get total int money inside VM
    public int getTotalMoney() {
        return money.calculateTotal();
    }

    // Get list of denoms in VM
    public ArrayList<String> getTotalDenoms() {
        return new ArrayList<String>(Arrays.asList(money.listDenoms().split("\n")));
    }

    // Get last restock date
    public Calendar getLastRestock() {
        return lastRestock;
    }

    // Get sold items
    public ArrayList<Item> getSoldItems() {
        return soldItems;
    }

    // Take money from VM if valid
    public boolean takeMoney(int amount) {
        switch (amount) {
            case 1: if (money.getC1() > 0) {money.removeC1(); return true;}
            case 5: if (money.getC5() > 0) {money.removeC5(); return true;}
            case 10: if (money.getC10() > 0) {money.removeC10(); return true;}
            case 20: if (money.getC20() > 0) {money.removeC20(); return true;}
            case 50: if (money.getC50() > 0) {money.removeC50(); return true;}
            case 100: if (money.getC100() > 0) {money.removeC100(); return true;}
            case 200: if (money.getC200() > 0) {money.removeC200(); return true;}
            case 500: if (money.getC500() > 0) {money.removeC500(); return true;}
            case 1000: if (money.getC1000() > 0) {money.removeC1000(); return true;}
        }
        
        return false;
    }

    // Return money of VM
    public Money getMoney() {
        return money;
    }

    // Adds given quantity to slot
    public void addQuantity(int slot, int quantity) {
        items.get(slot).addQuantity(quantity);
    }

    // Sets price of slot
    public void setPrice(int slot, int price) {
        items.get(slot).setPrice(price);
    }

    // Set new restock date
    public void setRestockDate(Date date) {
        lastRestock.setTime(date);
    }

    // Clear sold items list
    public void clearSoldItems() {
        soldItems.clear();
    }

    // Get items
    public ArrayList<Item> getItems() {
        return items;
    }

    // Get starting inventory
    public ArrayList<Integer> getStartingInventory() {
        return startingInventory;
    }

    // Set starting inventory on new restock
    public void setStartingInventory() {
        for (int i = 0; i < items.size(); i++) {
            startingInventory.set(i, items.get(i).getQuantity());
        }
    }
}