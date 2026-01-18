import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller implements ActionListener {
    private GUI gui;
    private Model model;
    private String location;

    // Add view and model classes
    public Controller(GUI gui, Model model) {
        this.gui = gui;
        this.model = model;
        location = "";

        gui.setMainActionListener(this);
        gui.mainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create menu
        if (e.getActionCommand().equals("Create a Vending Machine")) {
            gui.hideMm();
            gui.create();
            gui.setCreateActionListener(this);
        // Test menu if VM exists
        } else if (e.getActionCommand().equals("Test a Vending Machine")) {
            location = new String("test");
            if (model.vmExists()) {
                gui.hideMm();
                gui.test();
                gui.setTestActionListener(this);
            } else {
                gui.noVMError();
            }
        // Exit
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        // Create RVM
        } else if (e.getActionCommand().equals("Regular Vending Machine")) {
            model.createVM(true);
            gui.vmCreated();
            gui.hideCreate();
            gui.mainMenu();
            gui.setMainActionListener(this);
        // Create SVM
        } else if (e.getActionCommand().equals("Special Vending Machine")) {
            model.createVM(false);
            gui.vmCreated();
            gui.hideCreate();
            gui.mainMenu();
            gui.setMainActionListener(this);
        // Back button
        } else if (e.getActionCommand().equals("Back")) {
            if (location.equals("maintenance")) {
                gui.hideMaintenanceMenu();
                gui.test();
                gui.setTestActionListener(this);
                location = new String("test");
            }
            else {
                gui.hideCreate();
                gui.hideTest();
                gui.mainMenu();
                gui.setMainActionListener(this);
            }
        // Test vending features menu
        } else if (e.getActionCommand().equals("Test Vending Features")) {
            if (!model.isSpecial()) {
                gui.hideTest();
                location = new String("features");
                gui.vendingFeatures(gui.displayItems(model.getVMItemDetails()));
                gui.setVMFeaturesActionListener(this);
            } else {
                gui.hideTest();
                location = new String("specialfeatures");
                gui.vendingFeatures(gui.displayItems(model.getVMItemDetails()));
                gui.setVMFeaturesActionListener(this);
            }
        // Test maintenance features menu
        } else if (e.getActionCommand().equals("Test Maintenance Features")) {
            gui.hideTest();
            location = new String("maintenance");
            gui.maintenanceFeatures();
            gui.setMaintenanceActionListener(this);
        }
        // Select money
        else if (e.getActionCommand().startsWith("P")) {
            int p = Integer.parseInt(e.getActionCommand().substring(1));

            // Insert money for testing features
            if (location.equals("features") || location.equals("specialfeatures")) {
                model.addMoney(p, model.getUserMoney());
                gui.setDisplayMoney(model.getTotalUserMoney());
            } 
            // Collect money if valid
            else if (location.equals("collect")) {
                if (model.takeMoney(p)) {
                    gui.displayCollection(p);
                    gui.hideCollect();
                    gui.resetCollectActionListener(this);
                    gui.collectMoney(String.valueOf(model.getTotalVMMoney()), model.getTotalVMDenoms());
                    gui.setCollectActionListener(this);
                } 
                else {
                    gui.invalidCollection();
                }
            }
            // Replenish money
            else if (location.equals("replenish")) {
                model.addMoney(p, model.getVMMoney());
                gui.displayReplenish(p);
                gui.hideReplenish();
                gui.resetReplenishActionListener(this);
                gui.replenishMoney(String.valueOf(model.getTotalVMMoney()), model.getTotalVMDenoms());
                gui.setReplenishActionListener(this);
            }
        }
        // Select item
        else if (e.getActionCommand().startsWith("#")) {
            int slot = Integer.parseInt(e.getActionCommand().substring(1)) - 1;
            model.setCurSlot(slot);
            // Purchase item if in features
            if (location.equals("specialfeatures")) {
                if (!model.checkValidCombo(slot)) {
                    gui.invalidAddOn();
                } else {
                    model.addToPickedSlots(slot);
                    model.checkMeal(); 
                    gui.hidePickedItems();
                    gui.pickedItems(model.getPickedItemNames());

                    if (!gui.addAnotherItem()) {
                        if (!model.isValidPurchase()) {
                            gui.invalidPurchase();
                            model.resetPickedSlots();
                            gui.hidePickedItems();
                            gui.pickedItems(model.getPickedItemNames());
                        } else {
                            String change = model.selectItem();
                            if (change != null) {
                                // If valid, dispense, display change, go back to test menu
                                gui.resetVMFeaturesActionListener(this);

                                for (String s : model.getSteps()) {
                                    if (!s.equals(""))
                                        gui.displaySteps(s);
                                }

                                gui.displayDone(model.getCalories());
                                
                                gui.displayChange(change);
                                model.resetPickedSlots();
                                gui.hideVendingFeatures();
                                gui.hidePickedItems();
                                gui.test();
                                gui.setTestActionListener(this);
                            } else {
                                gui.notEnoughMoney();
                                gui.displayChange(model.dispenseMoney());
                                model.resetPickedSlots();
                                model.resetUserMoney();
                                gui.resetVMFeaturesActionListener(this);
                                gui.hideVendingFeatures();
                                gui.hidePickedItems();
                                gui.test();
                                gui.setTestActionListener(this);
                            }
                        }
                    }
                }
            }
            else if (location.equals("features")) {
                model.addToPickedSlots(slot);
                // Check if purchase is valid
                if (!model.isValidPurchase()) {
                    gui.invalidPurchase();
                    model.resetPickedSlots();
                }
                else 
                {
                    String change = model.selectItem();
                    if (change != null) {
                        // If valid, dispense, display change, go back to test menu
                        gui.resetVMFeaturesActionListener(this);
                        gui.dispensingItem(model.getItemName(slot), model.getCalories());
                        gui.displayChange(change);
                        model.resetPickedSlots();
                        gui.hideVendingFeatures();
                        gui.test();
                        gui.setTestActionListener(this);
                    } else {
                        gui.notEnoughMoney();
                        gui.displayChange(model.dispenseMoney());
                        model.resetPickedSlots();
                        model.resetUserMoney();
                        gui.resetVMFeaturesActionListener(this);
                        gui.hideVendingFeatures();
                        gui.test();
                        gui.setTestActionListener(this);
                    }
                }
            }
            // Move to restock menu
            else if (location.equals("restock")) {
                gui.hideRestock();
                gui.resetRestockActionListener(this);
                gui.itemRestockMenu(model.getItemName(model.getCurSlot()), model.getItemQuantity(model.getCurSlot()));
                gui.setAddItemActionListener(this);
            }
            // Move to set price menu
            else if (location.equals("price")) {
                gui.hidePrice();
                gui.resetRestockActionListener(this);
                gui.itemPriceMenu(model.getItemName(model.getCurSlot()), model.getItemPrice(model.getCurSlot()));
                gui.setItemPriceActionListener(this);
            }
            
        }
        else if (e.getActionCommand().equals("Meals")) {
            if (location.equals("specialfeatures")) {
                gui.hidePickedItems();
                gui.viewMeals(model.getMealsCombos());
            }
        }
        // Cancel button
        else if (e.getActionCommand().equals("Cancel")) {
            // Cancels transaction
            if (location.equals("features") || location.equals("specialfeatures")) {
                if (model.dispenseMoney() != null) {
                    gui.displayChange(model.dispenseMoney());
                    model.resetUserMoney();
                }

                gui.resetVMFeaturesActionListener(this);
                gui.hideVendingFeatures();
                model.resetPickedSlots();
                gui.hidePickedItems();
                gui.test();
                gui.setTestActionListener(this);
            } else if (location.equals("collect")) {
                gui.resetCollectActionListener(this);
                gui.hideCollect();
                location = new String("maintenance");
                gui.maintenanceFeatures();
                gui.setMaintenanceActionListener(this);
            } else if (location.equals("replenish")) {
                gui.resetReplenishActionListener(this);
                gui.hideReplenish();
                location = new String("maintenance");
                gui.maintenanceFeatures();
                gui.setMaintenanceActionListener(this);
            }
        }
        // Restock Menu
        else if (e.getActionCommand().equals("Restock Products")) {
            gui.hideMaintenanceMenu();
            String dateString = gui.promptForDateInput();
            if (dateString != null) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = formatter.parse(dateString);
                    model.updateLastRestockDate(date);
                } catch (Exception ex) {
                    gui.invalidDate();
                }
            }
            location = new String("restock");
            gui.restock(gui.displayItems(model.getVMItemDetails()));
            gui.setRestockActionListener(this);
        }
        // Restocks a product
        else if (e.getActionCommand().equals("Add Quantity")) {
            if (gui.getSpinnerValue() > 0) {
                model.addQuantity(model.getCurSlot(), gui.getSpinnerValue());
                gui.quantityAdded(model.getItemName(model.getCurSlot()), gui.getSpinnerValue());
            }

            gui.hideItemRestockMenu();
            gui.restock(gui.displayItems(model.getVMItemDetails()));
            gui.setRestockActionListener(this);
        } else if (e.getActionCommand().equals("Exit Restocking Menu")) {
            gui.resetRestockActionListener(this);
            gui.hideRestock();
            location = new String("maintenance");
            gui.maintenanceFeatures();
            gui.setMaintenanceActionListener(this);
        }
        // Exits a menu
        else if (e.getActionCommand().equals("Exit Menu")) {
            if (location.equals("price")) {
                gui.resetRestockActionListener(this);
                gui.hidePrice();
                location = new String("maintenance");
                gui.maintenanceFeatures();
                gui.setMaintenanceActionListener(this);
            } else if (location.equals("summary")) {
                gui.hideSummary();
                location = new String("maintenance");
                gui.maintenanceFeatures();
                gui.setMaintenanceActionListener(this);
            } else if (location.equals("inventories")) {
                gui.hideInventories();
                location = new String("summary");
                gui.displaySummary(model.getLastRestock(), model.getSoldItemNames(), model.getSoldItemQuantities(), model.getAmountCollected());
                gui.setSummaryActionListener(this);
            }
        }
        // Set Price Menu
        else if (e.getActionCommand().equals("Set Item Price")) {
            gui.hideMaintenanceMenu();
            location = new String("price");
            gui.setPrice(gui.displayItems(model.getVMItemDetails()));
            gui.setPriceActionListener(this);
        }
        // Sets item price
        else if (e.getActionCommand().equals("Set Price")) {
            model.setPrice(model.getCurSlot(), gui.getSpinnerValue());
            gui.hideItemPriceMenu();
            gui.setPrice(gui.displayItems(model.getVMItemDetails()));
            gui.setPriceActionListener(this);
        }
        // Collect money from VM
        else if (e.getActionCommand().equals("Collect Money")) {
            gui.hideMaintenanceMenu();
            location = new String("collect");
            gui.collectMoney(String.valueOf(model.getTotalVMMoney()), model.getTotalVMDenoms());
            gui.setCollectActionListener(this);
        // Replenish VM denominations
        } else if (e.getActionCommand().equals("Replenish Money")) {
            gui.hideMaintenanceMenu();
            location = new String("replenish");
            gui.replenishMoney(String.valueOf(model.getTotalVMMoney()), model.getTotalVMDenoms());
            gui.setReplenishActionListener(this);
        // Print summary of transactions
        } else if (e.getActionCommand().equals("Summary of Transactions")) {
            gui.hideMaintenanceMenu();
            location = new String("summary");
            gui.displaySummary(model.getLastRestock(), model.getSoldItemNames(), model.getSoldItemQuantities(), model.getAmountCollected());
            gui.setSummaryActionListener(this);
        } else if (e.getActionCommand().equals("View Inventories")) {
            gui.hideSummary();
            location = new String("inventories");
            gui.displayInventories(model.getItemNames(), model.getInitialInventory());
            gui.setInventoriesActionListener(this);
        } else if (e.getActionCommand().equals(">")) {
            gui.hideInventories();
            gui.setDisplayInventoryFinal();
            gui.displayInventories(model.getItemNames(), model.getFinalInventory());
            gui.setInventoriesActionListener(this);
        } else if (e.getActionCommand().equals("<")) {
            gui.hideInventories();
            gui.setDisplayInventoryInitial();
            gui.displayInventories(model.getItemNames(), model.getInitialInventory());
            gui.setInventoriesActionListener(this);
        }
    }
}
