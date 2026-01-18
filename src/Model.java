import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Model {
  private VendingMachine vm;
  private Money userMoney;
  private int curSlot;
  private ArrayList<Integer> pickedSlots;

  // Constructs a new model with 0 money and 0 picked slots
  public Model() {
    userMoney = new Money();
    pickedSlots = new ArrayList<Integer>();
  }

  // Returns true if a VM has already been created
  public boolean vmExists() {
    if (vm == null) {
      return false;
    }

    return true;
  }

  // Creates a regular or special VM
  public void createVM(boolean isRegular) {
    if (isRegular) {
      vm = new VendingMachine();
    } else {
      vm = new SpecialMachine();
    }
  }

  public VendingMachine getVM() {
    return vm;
  }

  public Money getUserMoney() {
    return userMoney;
  }

  // Gets details of items in VM formatted in a string
  public ArrayList<String> getVMItemDetails() {
    return vm.getItemDetails();
  }

  // Gets money in VM
  public Money getVMMoney() {
    return vm.getMoney();
  }

  // Gets int value of user's money
  public int getTotalUserMoney() {
    return userMoney.calculateTotal();
  }

  // Adds money of a given denomination
  public void addMoney(int value, Money m) {
    switch (value) {
      case 1:
        m.addC1();
        break;
      case 5:
        m.addC5();
        break;
      case 10:
        m.addC10();
        break;
      case 20:
        m.addC20();
        break;
      case 50:
        m.addC50();
        break;
      case 100:
        m.addC100();
        break;
      case 200:
        m.addC200();
        break;
      case 500:
        m.addC500();
        break;
      case 1000:
        m.addC1000();
        break;
    }
  }

  // Processes the user selecting items
  public String selectItem() {
    // Gets denominations of change
    Money change = getChange();

    // Cancel transaction if VM cannot produce change
    if (change == null) {
      return null;
    }

    // Add the user's money to VM
    vm.addMoney(userMoney);
    // Reset user's money
    userMoney = new Money();

    // Remove the item and add to sold items
    for (int slot : pickedSlots) {
      if (slot < 20) {
        vm.removeItem(slot);
        vm.addSoldItem(slot);
      } else {
        if (slot == 20) {
          int[] parts = { 5, 4, 10 };
          for (int i : parts) {
            vm.removeItem(i);
            vm.addSoldItem(i);
          }
        } else if (slot == 21) {
          int[] parts = { 6, 3, 11 };
          for (int i : parts) {
            vm.removeItem(i);
            vm.addSoldItem(i);
          }
        } else if (slot == 22) {
          int[] parts = { 7, 2, 13 };
          for (int i : parts) {
            vm.removeItem(i);
            vm.addSoldItem(i);
          }
        } else if (slot == 23) {
          int[] parts = { 8, 0, 12 };
          for (int i : parts) {
            vm.removeItem(i);
            vm.addSoldItem(i);
          }
        } else if (slot == 24) {
          int[] parts = { 9, 1, 13 };
          for (int i : parts) {
            vm.removeItem(i);
            vm.addSoldItem(i);
          }
        }
      }
    }

    return pay(change);
  }

  // Returns the change formatted in string
  public String pay(Money change) {
    if (!change.listDenoms().equals("")) {
      return "Change:\n" + change.listDenoms();
    }

    return "Exact amount paid, no change to be dispensed.";
  }

  // Calculates for the change
  public Money getChange() {
    int total = 0;

    for (int i : pickedSlots) {
      if (i < 20)
        total += vm.getPrices().get(i);
      else
        total += ((SpecialMachine) vm).getMeals().get(i - 20).getTotalPrice();
    }

    Money change = vm.getChange(getTotalUserMoney() - total);

    if (change == null) {
      return null;
    }

    return change;
  }

  // String dispensing different denominations of money when transaction is cancelled
  public String dispenseMoney() {
    if (!userMoney.listDenoms().equals("")) {
      return "Transaction cancelled. Dispensing...\n" + userMoney.listDenoms();
    }

    return null;
  }

  // Returns true if the user has enough money to pay for the item or the item's quantity is enough
  public boolean isValidPurchase() {
    int total = 0;
    ArrayList<Integer> tempSlots = new ArrayList<Integer>();

    for (int slot : pickedSlots) {
      if (slot < 20)
        tempSlots.add(slot);
      else {
        for (Item item : ((SpecialMachine) vm).getMeals().get(slot - 20).getItems())
          tempSlots.add(vm.findItem(item.getName(), vm.getItems()));
      }
    }

    for (int slot : tempSlots) {

      if (vm.getQuantities().get(slot) < Collections.frequency(tempSlots, slot))
        return false;
      total += vm.getPrices().get(slot);

    }

    if (getTotalUserMoney() >= total)
      return true;

    return false;
  }

  // Reset user's money to 0
  public void resetUserMoney() {
    userMoney = new Money();
  }

  // Gets item name given a slot
  public String getItemName(int slot) {
    return vm.getItemName(slot);
  }

  // Gets total calories of all picked items
  public int getCalories() {
    int total = 0;

    for (int i : pickedSlots) {
      if (i < 20)
        total += vm.getItemCalories(i);
      else
        total += ((SpecialMachine) vm).getMeals().get(i - 20).getTotalCalories();
    }

    return total;
  }

  // Gets item quantity given a slot
  public int getItemQuantity(int slot) {
    return vm.getItemQuantity(slot);
  }

  // Gets item price given a slot
  public int getItemPrice(int slot) {
    int total = 0;
    if (slot < 20)
      return total += vm.getPrices().get(slot);
    else
      return total += ((SpecialMachine) vm).getMeals().get(slot - 20).getTotalPrice();
  }

  // Gets integer amount of total money in VM
  public int getTotalVMMoney() {
    return vm.getTotalMoney();
  }

  // Gets an string array of total denominations in VM
  public ArrayList<String> getTotalVMDenoms() {
    return vm.getTotalDenoms();
  }

  // Gets formatted date of last VM restock
  public String getLastRestock() {
    Calendar lastRestock = vm.getLastRestock();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    return sdf.format(lastRestock.getTime());
  }

  // Gets formatted item names of all VM items
  public ArrayList<String> getItemNames() {
    ArrayList<String> itemNames = new ArrayList<String>();

    for (Item i : vm.getItems()) {
      itemNames.add(i.getName());
    }

    return itemNames;
  }

  // Gets formatted item names of all sold VM items
  public ArrayList<String> getSoldItemNames() {
    ArrayList<String> itemNames = new ArrayList<String>();

    for (Item i : vm.getSoldItems()) {
      itemNames.add(i.getName());
    }

    return itemNames;
  }

  // Gets formatted item names of all picked VM items
  public ArrayList<String> getPickedItemNames() {
    ArrayList<String> itemNames = new ArrayList<String>();

    for (int i : pickedSlots) {
      if (i < 20)
        itemNames.add(vm.getItemName(i));
      else
        itemNames.add(((SpecialMachine) vm).getMeals().get(i - 20).getMealName());
    }

    return itemNames;
  }

  // Gets list of all possible meal combinations
  public ArrayList<String> getMealsCombos() {
    ArrayList<String> mealCombos = new ArrayList<String>();
    SpecialMachine curVm = (SpecialMachine) vm;

    for (Meal meal : curVm.getMeals()) {
      mealCombos.add(meal.getMealCombo());
    }

    return mealCombos;
  }

  // Gets quantities of initial inventory
  public ArrayList<Integer> getInitialInventory() {
    return vm.getStartingInventory();
  }

  // Gets quantities of final inventory
  public ArrayList<Integer> getFinalInventory() {
    ArrayList<Integer> quants = new ArrayList<Integer>();

    for (Item i : vm.getItems()) {
      quants.add(i.getQuantity());
    }

    return quants;
  }

  // Gets quantities of sold items
  public ArrayList<Integer> getSoldItemQuantities() {
    ArrayList<Integer> quantities = new ArrayList<Integer>();

    for (Item i : vm.getSoldItems()) {
      quantities.add(i.getQuantity());
    }

    return quantities;
  }

  // Gets amount collected since last restock
  public int getAmountCollected() {
    int total = 0;

    for (Item i : vm.getSoldItems()) {
      total += i.getPrice();
    }

    return total;
  }

  // Take money from VM
  public boolean takeMoney(int amount) {
    return vm.takeMoney(amount);
  }

  // Adds quantity to slot
  public void addQuantity(int slot, int quantity) {
    vm.addQuantity(slot, quantity);
    vm.clearSoldItems();
    vm.setStartingInventory();
  }

  // Update the last restock date to given value
  public void updateLastRestockDate(Date date) {
    vm.setRestockDate(date);
  }

  // Gets last slot picked by user
  public int getCurSlot() {
    return curSlot;
  }

  // Updates last slot picked
  public void setCurSlot(int curSlot) {
    this.curSlot = curSlot;
  }

  // Updates price of item
  public void setPrice(int slot, int price) {
    vm.setPrice(slot, price);
  }

  // Returns true if VM is SVM
  public boolean isSpecial() {
    return vm instanceof SpecialMachine;
  }

  // Resets slots picked by user
  public void resetPickedSlots() {
    pickedSlots.clear();
  }

  // Add slot to picked slots
  public void addToPickedSlots(int slot) {
    pickedSlots.add(slot);
  }

  // Get picked slots
  public ArrayList<Integer> getPickedSlots() {
    return pickedSlots;
  }

  // Get steps of picked slots
  public ArrayList<String> getSteps() {
    SpecialMachine curVm = (SpecialMachine) vm;
    ArrayList<String[]> steps = new ArrayList<String[]>();
    ArrayList<String> sortedSteps = new ArrayList<String>();
    ArrayList<Integer> orderSlots = new ArrayList<Integer>();
    for (int slot : pickedSlots) {
      if (slot < 20)
        orderSlots.add(slot);
      else {
        for (Item item : ((SpecialMachine) vm).getMeals().get(slot - 20).getItems())
          orderSlots.add(vm.findItem(item.getName(), vm.getItems()));
      }
    }
    boolean riceDone = false;
    boolean mainDone = false;
    boolean sidesDone = false;
    boolean sauceDone = false;
    boolean allDone = false;
    int i;
    int curSlot = 0;

    // Add steps according to item type
    while (orderSlots.size() > 0 || !allDone) {
      i = 0;
      while (!riceDone && !allDone) {
        curSlot = orderSlots.get(i);
        if (curSlot != -1 && (curSlot >= 0 && curSlot <= 4)) {
          String[] curSteps = curVm.getItemSteps(curSlot);
          steps.add(curSteps);
          orderSlots.remove(i);
          i = 0;
        } else {
          i++;
        }

        if (i >= orderSlots.size())
          riceDone = true;

        if (orderSlots.size() == 0)
          allDone = true;
      }

      i = 0;
      while (!mainDone && !allDone) {
        curSlot = orderSlots.get(i);
        if (curSlot != -1 && (curSlot >= 5 && curSlot <= 9)) {
          String[] curSteps = curVm.getItemSteps(curSlot);
          steps.add(curSteps);
          orderSlots.remove(i);
          i = 0;
        } else {
          i++;
        }

        if (i >= orderSlots.size())
          mainDone = true;

        if (orderSlots.size() == 0)
          allDone = true;
      }

      i = 0;
      while (!sidesDone && !allDone) {
        curSlot = orderSlots.get(i);
        if (curSlot != -1 && (curSlot >= 10 && curSlot <= 14)) {
          String[] curSteps = curVm.getItemSteps(curSlot);
          steps.add(curSteps);
          orderSlots.remove(i);
          i = 0;
        } else {
          i++;
        }

        if (i >= orderSlots.size())
          sidesDone = true;

        if (orderSlots.size() == 0)
          allDone = true;
      }

      i = 0;
      while (!sauceDone && !allDone) {
        curSlot = orderSlots.get(i);
        if (curSlot != -1 && (curSlot >= 15 && curSlot <= 19)) {
          String[] curSteps = curVm.getItemSteps(curSlot);
          steps.add(curSteps);
          orderSlots.remove(i);
          i = 0;
        } else {
          i++;
        }

        if (i >= orderSlots.size())
          sauceDone = true;

        if (orderSlots.size() == 0)
          allDone = true;
      }
    }

    // Sort steps
    for (int j = 0; j < 5; j++) {
      for (String[] curSteps : steps) {
        if (j < curSteps.length && curSteps[j] != "") {
          sortedSteps.add(curSteps[j]);
        }
      }
    }

    return sortedSteps;
  }

  // Checks if combination is valid
  public boolean checkValidCombo(int slot) {
    if (slot <= 14)
      return true;

    switch (slot) {
      case 15:
        if (pickedSlots.contains(5) || pickedSlots.contains(6) || pickedSlots.contains(7) || pickedSlots.contains(8)
            || pickedSlots.contains(9))
          return true;
        break;
      case 16:
        if (pickedSlots.contains(7) || pickedSlots.contains(8) || pickedSlots.contains(9))
          return true;
        break;
      case 17:
        if (pickedSlots.contains(5))
          return true;
        break;
      case 18:
        if (pickedSlots.contains(5) || pickedSlots.contains(7))
          return true;
        break;
      case 19:
        if (pickedSlots.contains(6) || pickedSlots.contains(7) || pickedSlots.contains(8) || pickedSlots.contains(9))
          return true;
        break;

      default:
        break;
    }

    return false;
  }

  // Combines picked slots into meals
  public boolean checkMeal() {
    if (pickedSlots.contains(5) && pickedSlots.contains(4) && pickedSlots.contains(10)) {
      pickedSlots.remove(Integer.valueOf(5));
      pickedSlots.remove(Integer.valueOf(4));
      pickedSlots.remove(Integer.valueOf(10));
      pickedSlots.add(20);
    } else if (pickedSlots.contains(6) && pickedSlots.contains(3) && pickedSlots.contains(11)) {
      pickedSlots.remove(Integer.valueOf(6));
      pickedSlots.remove(Integer.valueOf(3));
      pickedSlots.remove(Integer.valueOf(11));
      pickedSlots.add(21);
    } else if (pickedSlots.contains(7) && pickedSlots.contains(2) && pickedSlots.contains(13)) {
      pickedSlots.remove(Integer.valueOf(7));
      pickedSlots.remove(Integer.valueOf(2));
      pickedSlots.remove(Integer.valueOf(13));
      pickedSlots.add(22);
    } else if (pickedSlots.contains(8) && pickedSlots.contains(0) && pickedSlots.contains(12)) {
      pickedSlots.remove(Integer.valueOf(8));
      pickedSlots.remove(Integer.valueOf(0));
      pickedSlots.remove(Integer.valueOf(12));
      pickedSlots.add(23);
    } else if (pickedSlots.contains(9) && pickedSlots.contains(1) && pickedSlots.contains(13)) {
      pickedSlots.remove(Integer.valueOf(9));
      pickedSlots.remove(Integer.valueOf(1));
      pickedSlots.remove(Integer.valueOf(13));
      pickedSlots.add(24);
    }
    return false;
  }
}
