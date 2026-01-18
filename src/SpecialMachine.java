import java.util.ArrayList;

public class SpecialMachine extends VendingMachine {
    private String[][] steps;
    private ArrayList<Meal> meals;

    // Creates an instance of the SpecialMachine class.
    public SpecialMachine() {
        super();
        this.items = defaultSpecialItems(this.items);
        this.steps = defaultSteps();
        this.meals = defaultMeals();
    }

    // Sets the SVM's items to default settings.
    private ArrayList<Item> defaultSpecialItems(ArrayList<Item> items) {
        String[] names = {"Soy Sauce", "BBQ Sauce", "Dashi", "Chili Bean Paste", "Honey"};
        int[] calories = {9, 29, 16, 10, 64};

        for(int i = 0; i < 5; i++) {
        Item cur = new Item(names[i], calories[i], 10, 10);
        items.add(cur);
        }

        return items;
    }

    // Sets the SVM's steps to the default settings.
    private static String[][] defaultSteps() {
        String[] stepsFriedRice = {"Cooking rice...",
                                "Stir-frying rice with eggs, carrots, peas, and garlic...",
                                "Putting fried rice in bowl..."};
    
        String[] stepsGarlicRice = {"Cooking rice...",
                                    "Cooking garlic...",
                                    "Frying rice with garlic and salt...",
                                    "Putting garlic fried rice in bowl..."};
    
        String[] stepsJavaRice = {"Cooking rice...",
                                "Mixing turmeric and annatto powder...",
                                "Cooking java mix with butter, garlic, onion, and shallot...",
                                "Mixing java mix with rice...",
                                "Putting java rice in bowl..."};
    
        String[] stepsWhiteRice = {"Cooking rice...",
                                "Putting rice in bowl..."};
    
        String[] stepsBrownRice = {"Cooking rice...",
                                "Putting brown rice in bowl..."};
    
        //Main
        String[] stepsCurry = {"Cooking beef...",
                            "Making curry sauce...",
                            "Simmering beef with curry sauce...",
                            "",
                            "Pouring curry..."};
    
        String[] stepsNatto = {"","","","","Topping with natto..."};
    
        String[] stepsBBQChicken = {"Mixing chicken with soy dressing...",
                                    "Barbecuing chicken...",
                                    "","",
                                    "Topping with BBQ Chicken..."};
    
        String[] stepsBeef = {"Cooking onion and seasonings mix...",
                            "Simmering beef with onion and seasonings mix...",
                            "","",
                            "Topping with beef..."};
    
        String[] stepsPork = {"Stir-frying pork...",
                            "","","",
                            "Topping with pork..."};
    
        //Side
        String[] stepsCarrot = {"","","", "Mixing with carrots..."};
    
        String[] stepsNori = {"","","","","Topping with nori..."};
    
        String[] stepsRedChiliPepper = {"","","","Mixing with red chili peppers..."};
    
        String[] stepsEgg = {"Cooking egg...",
                            "","","",
                            "Topping with egg..."};
    
        String[] stepsGreenOnion = {"","","","", "Topping with green onion..."};
    
        //Soups and Sauces
        String[] stepsSoySauce = {"","","","","Dashing with soy sauce..."};
    
        String[] stepsBBQSauce = {"","","","Mixing with BBQ sauce..."};
    
        String[] stepsDashi = {"","","","","Pouring dashi..."};
    
        String[] stepsChiliBeanPaste = {"","","","Mixing with chili bean paste..."};
    
        String[] stepsHoney = {"","","","Mixing with honey..."};
    
        String[][] steps = {stepsFriedRice, stepsGarlicRice, stepsJavaRice, stepsWhiteRice, stepsBrownRice,
                        stepsCurry, stepsNatto, stepsBBQChicken, stepsBeef, stepsPork, 
                        stepsCarrot, stepsNori, stepsRedChiliPepper, stepsEgg, stepsGreenOnion, 
                        stepsSoySauce, stepsBBQSauce, stepsDashi, stepsChiliBeanPaste, stepsHoney};

        return steps;
    }
      // Sets the meals in the SVM to default settings.
    private ArrayList<Meal> defaultMeals() {
        ArrayList<Meal> newMeals = new ArrayList<Meal>();

        String[] mealNames = {"Curry Meal", "Natto Meal", "BBQ Chicken Meal", "Beef Meal", "Pork Meal"};

        Item[] curryItems = {items.get(5), items.get(4), items.get(10)};
        Item[] nattoItems = {items.get(6), items.get(3), items.get(11)};
        Item[] bbqChickenItems = {items.get(7), items.get(2), items.get(14)};
        Item[] beefItems = {items.get(8), items.get(0), items.get(12)};
        Item[] porkItems = {items.get(9), items.get(1), items.get(13)};

        Item[][] allItems = {curryItems, nattoItems, bbqChickenItems, beefItems, porkItems};

        for (int i = 0; i < 5; i++) {
            Meal cur = new Meal(mealNames[i], allItems[i]);
            newMeals.add(cur);
        }
        meals = new ArrayList<Meal>(newMeals);
        return meals;
    }

    // Returns the steps of the item in the specified slot.
    public String[] getItemSteps(int slot) {
        String[] steps = this.steps[slot];

        return steps;
    }

    // Returns the SVM's meals.
    public ArrayList<Meal> getMeals() {
        return meals;
    }
}
