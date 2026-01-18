import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    // JFrames
    private JFrame mm;
    private JFrame create;
    private JFrame test;
    private JFrame vmFeatures;
    private JFrame pickedItems;
    private JFrame viewMeals; 
    private JFrame maintenanceFeatures;
    private JFrame restock;
    private JFrame itemRestockMenu;
    private JFrame setPrice;
    private JFrame itemPriceMenu;
    private JFrame collect;
    private JFrame replenish;
    private JFrame summary;
    private JFrame inventories;

    // Main menu buttons
    private JButton btnCreate;
    private JButton btnTest;
    private JButton btnExit;

    // Vending machine type buttons
    private JButton btnRegular;
    private JButton btnSpecial;

    private JButton btnBack;

    // Test vending features buttons
    private JButton btnTestVending;
    private JButton btnTestMaintenance;
    
    // Vending machine buttons
    private ArrayList<JButton> itemButtons;
    private ArrayList<JButton> moneyButtons;
    private JButton btnMeals; 

    private JButton btnCancel;

    // Maintenance feature buttons
    private JButton btnRestock;
    private JButton btnSetPrice;
    private JButton btnCollect;
    private JButton btnReplenish;
    private JButton btnSummary;

    private JLabel lblMoney;
    private JLabel lblInvent;

    private JButton btnView;

    private JButton btnAdd;
    private JButton btnStopRestock;
    private JButton btnSet;
    private JButton btnStop;
    private JButton btnLeft;
    private JButton btnRight;

    private JSpinner s;

    // Initialize buttons and JFrames upon construction
    public GUI() {
        moneyButtons = initMoneyButtons();
        mm = initFrame();
        create = initFrame();
        test = initFrame();
        vmFeatures = initFrame();
        pickedItems = initFrame();
        viewMeals = initFrame(); 
        maintenanceFeatures = initFrame();
        restock = initFrame();
        itemRestockMenu = initFrame();
        setPrice = initFrame();
        itemPriceMenu = initFrame();
        collect = initFrame();
        replenish = initFrame();
        summary = initFrame();
        inventories = initFrame();
        lblInvent = new JLabel("Starting Inventory");
        mainMenu();
    }

    // Default JFrame
    public JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(450, 500);
        frame.setVisible(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    // Main menu
    public JFrame mainMenu() {
        // NORTH PANEL - Header
        JPanel panelNorth = initNorthPanel();

        JLabel lblTitle = new JLabel("RICE BOWL Vending Machine Factory");
        setHeading(lblTitle);
        panelNorth.add(lblTitle);

        JLabel lblMm = new JLabel("Main Menu");
        setSubHeading(lblMm);
        panelNorth.add(lblMm);

        // CENTER PANEL - Main menu
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3,0,0,8));
        panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnCreate = new JButton("Create a Vending Machine");
        setButtonText(btnCreate);
        panelCenter.add(btnCreate);

        btnTest = new JButton("Test a Vending Machine");
        setButtonText(btnTest);
        panelCenter.add(btnTest);

        btnExit = new JButton("Exit");
        setButtonText(btnExit);
        panelCenter.add(btnExit);

        mm.add(panelNorth, BorderLayout.NORTH);
        mm.add(panelCenter, BorderLayout.CENTER);
        mm.setVisible(true);

        return mm;

    }

    // Create menu
    public JFrame create() {
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();

        JLabel lblCreate = new JLabel("Create a Vending Machine");
        setHeading(lblCreate);

        JLabel lblVMType = new JLabel("What type of vending machine?");
        setSubHeading(lblVMType);

        panelNorth.add(lblCreate);
        panelNorth.add(lblVMType);

        // CENTER PANEL - Vending machine types
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3,0,0,8));
        panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnRegular = new JButton("Regular Vending Machine");
        setButtonText(btnRegular);
        panelCenter.add(btnRegular);

        btnSpecial = new JButton("Special Vending Machine");
        setButtonText(btnSpecial);
        panelCenter.add(btnSpecial);

        btnBack = new JButton("Back");
        setButtonText(btnBack);
        panelCenter.add(btnBack);

        create.add(panelNorth, BorderLayout.NORTH);
        create.add(panelCenter, BorderLayout.CENTER);
        create.setVisible(true);

        return create;
    }

    // Test menu
    public JFrame test() {
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTest = new JLabel("Test Menu");
        setHeading(lblTest);

        panelNorth.add(lblTest);
        panelNorth.setVisible(true);

        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3,0,0,8));
        panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnTestVending = new JButton("Test Vending Features");
        setButtonText(btnTestVending);
        panelCenter.add(btnTestVending);

        btnTestMaintenance = new JButton("Test Maintenance Features");
        setButtonText(btnTestMaintenance);
        panelCenter.add(btnTestMaintenance);

        btnBack = new JButton("Back");
        setButtonText(btnBack);
        panelCenter.add(btnBack);

        test.add(panelNorth, BorderLayout.NORTH);
        test.add(panelCenter, BorderLayout.CENTER);
        test.setVisible(true);

        return test;
    }

    // Vending features
    public JFrame vendingFeatures(JPanel itemDisplay) {
        vmFeatures = initFrame();

        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();

        JLabel lblFeatures = new JLabel("RICE BOWL Vending Machine");
        setHeading(lblFeatures);
        panelNorth.add(lblFeatures);

        JLabel lblVMFeatures = new JLabel("Insert money and select.");
        setSubHeading(lblVMFeatures);
        panelNorth.add(lblVMFeatures);

        vmFeatures.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL - display all items
        vmFeatures.add(itemDisplay, BorderLayout.CENTER);

        // SOUTH PANEL - display money buttons
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridBagLayout());
        panelSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints c = new GridBagConstraints();

        lblMoney = new JLabel("Your money: 0  ");
        c.gridx = 0;
        c.gridy = 0;
        panelSouth.add(lblMoney);

        btnMeals = new JButton("Meals");
        setButtonText(btnMeals);
        panelSouth.add(btnMeals);

	
        // Bills buttons
        JLabel lblBills = new JLabel("Insert bill:");
        lblBills.setForeground(Color.BLACK);
        lblBills.setFont(new Font("Verdana", Font.PLAIN, 10));
        c.gridx = 0;
        c.gridy = 1;
        panelSouth.add(lblBills, c);

        for (int i = 0; i <= 4; i++) {
            c.gridx++;
            panelSouth.add(moneyButtons.get(i), c);
        }

        // Coins buttons
        JLabel lblCoins = new JLabel("Insert coin:");
        lblCoins.setForeground(Color.BLACK);
        lblCoins.setFont(new Font("Verdana", Font.PLAIN, 10));
        c.gridx = 0;
        c.gridy++;
        panelSouth.add(lblCoins, c);

        for (int i = 5; i <= 8; i++) {
            c.gridx++;
            panelSouth.add(moneyButtons.get(i), c);
        }

        btnCancel = new JButton("Cancel");
        setButtonText(btnCancel);
        c.gridx++;
        panelSouth.add(btnCancel, c);

        vmFeatures.add(panelSouth, BorderLayout.SOUTH);
        vmFeatures.setVisible(true);

        return vmFeatures;
    }

    public JFrame pickedItems(ArrayList<String> items) {
        pickedItems = initFrame();
        pickedItems.setSize(450,250);
        pickedItems.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblPick = new JLabel("Picked Items");
        setHeading(lblPick);

        panelNorth.add(lblPick);

        // CENTER PANEL
        JPanel panelCenter = new JPanel(new BorderLayout());

        // List of Transactions
        JPanel listItems = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        listItems.setBorder(new EmptyBorder(10, 10,10,10));

        c.gridx = 0;
        c.gridy = 0;

        for (int i = 0; i < items.size(); i++) {
            c.gridy++;
            listItems.add(new JLabel(items.get(i)), c);
        }

        
        panelCenter.add(listItems, BorderLayout.NORTH);
        JScrollPane panelPane = new JScrollPane(panelCenter);
        pickedItems.add(panelPane);

        pickedItems.add(panelNorth, BorderLayout.NORTH);
        pickedItems.setLocationRelativeTo(null);
        pickedItems.setVisible(true);
        return pickedItems;
    }

    public JFrame viewMeals(ArrayList<String> meals) {
        viewMeals = initFrame();
        viewMeals.setSize(450, 250);
        viewMeals.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblPick = new JLabel("Meals");
        setHeading(lblPick);

        panelNorth.add(lblPick);

        // CENTER PANEL
        JPanel panelCenter = new JPanel(new BorderLayout());

        // List of Transactions
        JPanel listMeals = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        listMeals.setBorder(new EmptyBorder(10, 10,10,10));

        c.gridx = 0;
        c.gridy = 0;

        for (int i = 0; i < meals.size(); i++) {
            c.gridy++;
            listMeals.add(new JLabel(meals.get(i)), c);
        }

        panelCenter.add(listMeals, BorderLayout.NORTH);
        JScrollPane panelPane = new JScrollPane(panelCenter);
        viewMeals.add(panelPane);

        viewMeals.add(panelNorth, BorderLayout.NORTH);
        viewMeals.setLocationRelativeTo(null);
        viewMeals.setVisible(true);
        return viewMeals;
    }

    // Test maintenance features
    public JFrame maintenanceFeatures() {
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblMaint = new JLabel("Test Maintenance Features");
        setHeading(lblMaint);

        panelNorth.add(lblMaint);

        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(6,0,0,8));
        panelCenter.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnRestock = new JButton("Restock Products");
        setButtonText(btnRestock);
        panelCenter.add(btnRestock);

        btnSetPrice = new JButton("Set Item Price");
        setButtonText(btnSetPrice);
        panelCenter.add(btnSetPrice);

        btnCollect = new JButton("Collect Money");
        setButtonText(btnCollect);
        panelCenter.add(btnCollect);

        btnReplenish = new JButton("Replenish Money");
        setButtonText(btnReplenish);
        panelCenter.add(btnReplenish);

        btnSummary = new JButton("Summary of Transactions");
        setButtonText(btnSummary);
        panelCenter.add(btnSummary);

        btnBack = new JButton("Back");
        setButtonText(btnBack);
        panelCenter.add(btnBack);

        maintenanceFeatures.add(panelNorth, BorderLayout.NORTH);
        maintenanceFeatures.add(panelCenter, BorderLayout.CENTER);
        maintenanceFeatures.setVisible(true);

        return maintenanceFeatures;
    }

    // Restock frame
    public JFrame restock(JPanel itemDisplay) {
        restock = initFrame();
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();

        JLabel lblRestock = new JLabel("Restock");
        setHeading(lblRestock);
        panelNorth.add(lblRestock);

        JLabel lblProduct = new JLabel("Which product do you want to restock?");
        setSubHeading(lblProduct);
        panelNorth.add(lblProduct);

        restock.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL
        restock.add(itemDisplay, BorderLayout.CENTER);

        // SOUTH PANEL
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        btnStopRestock = new JButton("Exit Restocking Menu");
        setButtonText(btnStopRestock);
        panelSouth.add(btnStopRestock);

        restock.add(panelSouth, BorderLayout.SOUTH);
        restock.setVisible(true);

        return restock;
    }

    public String promptForDateInput() {
        return JOptionPane.showInputDialog(null, "Enter the date (MM/dd/yyyy):");
    }

    public JFrame itemRestockMenu(String itemName, int quantity) {
        itemRestockMenu = initFrame();
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblRestockMenu = new JLabel(itemName);
        setHeading(lblRestockMenu);
        panelNorth.add(lblRestockMenu);

        itemRestockMenu.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        //panelCenter.setLayout(new GridBagLayout());
        panelCenter.setLayout(new GridLayout(3,0));
        panelCenter.setBorder(new EmptyBorder(10,10,10,10));

        JLabel curQuant = new JLabel("Current quantity: " + quantity);
        panelCenter.add(curQuant);

        SpinnerModel sm = new SpinnerNumberModel(0, 0, 100, 1);
        s = new JSpinner(sm);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor)s.getEditor();
        editor.getTextField().setEditable(false);

        panelCenter.add(s);


        btnAdd = new JButton("Add Quantity");
        setButtonText(btnAdd);
        panelCenter.add(btnAdd);

        itemRestockMenu.add(panelCenter, BorderLayout.CENTER);
        itemRestockMenu.setVisible(true);

        return itemRestockMenu;
    }
    
    // Set price frame
    public JFrame setPrice(JPanel itemDisplay) {
        setPrice = initFrame();
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();

        JLabel lblRestock = new JLabel("Set Item Price");
        setHeading(lblRestock);
        panelNorth.add(lblRestock);

        JLabel lblProduct = new JLabel("Which product do you want to edit?");
        setSubHeading(lblProduct);
        panelNorth.add(lblProduct);

        setPrice.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL
        setPrice.add(itemDisplay, BorderLayout.CENTER);

        // SOUTH PANEL - display exit button
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        btnStop = new JButton("Exit Menu");
        setButtonText(btnStop);
        panelSouth.add(btnStop);

        setPrice.add(panelSouth, BorderLayout.SOUTH);
        setPrice.setVisible(true);

        return setPrice;
    }

    public JFrame itemPriceMenu(String itemName, int price) {
        itemPriceMenu = initFrame();
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblRestockMenu = new JLabel(itemName);
        setHeading(lblRestockMenu);
        panelNorth.add(lblRestockMenu);

        itemPriceMenu.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        //panelCenter.setLayout(new GridBagLayout());
        panelCenter.setLayout(new GridLayout(3,0));
        panelCenter.setBorder(new EmptyBorder(10,10,10,10));

        JLabel curPrice = new JLabel("Current price: " + price);
        panelCenter.add(curPrice);

        SpinnerModel sm = new SpinnerNumberModel(price, 0, 5000, 1);
        s = new JSpinner(sm);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor)s.getEditor();
        editor.getTextField().setEditable(false);

        panelCenter.add(s);

        btnSet = new JButton("Set Price");
        setButtonText(btnSet);
        panelCenter.add(btnSet);

        itemPriceMenu.add(panelCenter, BorderLayout.CENTER);
        itemPriceMenu.setVisible(true);

        return itemPriceMenu;
    }

    // Collect money frame
    public JFrame collectMoney(String totalMoney, ArrayList<String> denoms) {
        collect = initFrame();
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();

        JLabel lblRestock = new JLabel("Collect Money");
        setHeading(lblRestock);
        panelNorth.add(lblRestock);

        JLabel lblProduct = new JLabel("How much money do you want to collect?");
        setSubHeading(lblProduct);
        panelNorth.add(lblProduct);

        collect.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL - display money in vending machine
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(0,1));
        panelCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel lblTotalMoney = new JLabel("Vending machine contains: P" + totalMoney);
        setSubHeading(lblTotalMoney);
        panelCenter.add(lblTotalMoney);

        for (String s : denoms) {
            JLabel lblDenom = new JLabel(s);
            panelCenter.add(lblDenom);
        }

        collect.add(panelCenter, BorderLayout.CENTER);

        // SOUTH PANEL - display money buttons
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridBagLayout());
        panelSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints c = new GridBagConstraints();

        // Bills buttons
        JLabel lblBills = new JLabel("Collect bill:");
        lblBills.setForeground(Color.BLACK);
        lblBills.setFont(new Font("Verdana", Font.PLAIN, 10));
        c.gridx = 0;
        c.gridy = 0;
        panelSouth.add(lblBills, c);

        for (int i = 0; i <= 4; i++) {
            c.gridx++;
            panelSouth.add(moneyButtons.get(i), c);
        }

        // Coins buttons
        JLabel lblCoins = new JLabel("Collect coin:");
        lblCoins.setForeground(Color.BLACK);
        lblCoins.setFont(new Font("Verdana", Font.PLAIN, 10));
        c.gridx = 0;
        c.gridy++;
        panelSouth.add(lblCoins, c);

        for (int i = 5; i <= 8; i++) {
            c.gridx++;
            panelSouth.add(moneyButtons.get(i), c);
        }

        btnCancel = new JButton("Cancel");
        setButtonText(btnCancel);
        c.gridx++;
        panelSouth.add(btnCancel, c);

        collect.add(panelSouth, BorderLayout.SOUTH);
        collect.setVisible(true);

        return collect;
    }

     // Collect money frame
    public JFrame replenishMoney(String totalMoney, ArrayList<String> denoms) {
        replenish = initFrame();

        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();

        JLabel lblRestock = new JLabel("Replenish Money");
        setHeading(lblRestock);
        panelNorth.add(lblRestock);

        JLabel lblProduct = new JLabel("How much money do you want to replenish?");
        setSubHeading(lblProduct);
        panelNorth.add(lblProduct);

        replenish.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL - display money in vending machine
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(0,1));
        panelCenter.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel lblTotalMoney = new JLabel("Vending machine contains: P" + totalMoney);
        setSubHeading(lblTotalMoney);
        panelCenter.add(lblTotalMoney);

        for (String s : denoms) {
            JLabel lblDenom = new JLabel(s);
            panelCenter.add(lblDenom);
        }

        replenish.add(panelCenter, BorderLayout.CENTER);

        // SOUTH PANEL - display money buttons
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridBagLayout());
        panelSouth.setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints c = new GridBagConstraints();

        // Bills buttons
        JLabel lblBills = new JLabel("Replenish bill:");
        lblBills.setForeground(Color.BLACK);
        lblBills.setFont(new Font("Verdana", Font.PLAIN, 10));
        c.gridx = 0;
        c.gridy = 0;
        panelSouth.add(lblBills, c);

        for (int i = 0; i <= 4; i++) {
            c.gridx++;
            panelSouth.add(moneyButtons.get(i), c);
        }

        // Coins buttons
        JLabel lblCoins = new JLabel("Replenish coin:");
        lblCoins.setForeground(Color.BLACK);
        lblCoins.setFont(new Font("Verdana", Font.PLAIN, 10));
        c.gridx = 0;
        c.gridy++;
        panelSouth.add(lblCoins, c);

        for (int i = 5; i <= 8; i++) {
            c.gridx++;
            panelSouth.add(moneyButtons.get(i), c);
        }

        btnCancel = new JButton("Cancel");
        setButtonText(btnCancel);
        c.gridx++;
        panelSouth.add(btnCancel, c);

        replenish.add(panelSouth, BorderLayout.SOUTH);
        replenish.setVisible(true);

        return replenish;
    }

    public JFrame displaySummary(String date, ArrayList<String> itemNames, ArrayList<Integer> itemQuantities, int total) {
        // NORTH PANEL
        summary = initFrame();
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10,10,10,10));

        JLabel lblSummary = new JLabel("Summary of Transactions");
        setHeading(lblSummary);
        panelNorth.add(lblSummary);

        summary.add(panelNorth, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel panelCenter = new JPanel(new BorderLayout());

        // List of Transactions
        JPanel listTransactions = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        listTransactions.setBorder(new EmptyBorder(10, 10,10,10));

        JLabel lblTransactions = new JLabel("List of Transactions");
        setSubHeading(lblTransactions);
        c.gridx = 0;
        c.gridy = 0;
        listTransactions.add(lblTransactions, c);
        c.gridy++;
        listTransactions.add(new JLabel("from last restock date of " + date), c);

        int i;

        // Display each item name and quantity
        for (i = 0; i < itemNames.size(); i++) {
            c.gridy++;
            listTransactions.add(new JLabel(itemNames.get(i) + " - " + itemQuantities.get(i)), c);
        }

        if (i == 0) {
            c.gridy++;
            listTransactions.add(new JLabel("No transactions found."), c);
        }

        c.gridy++;
        listTransactions.add(new JLabel("Total amount earned: P" + total), c);
        panelCenter.add(listTransactions, BorderLayout.NORTH);
        JScrollPane panelPane = new JScrollPane(panelCenter);
        summary.add(panelPane);

        // SOUTH PANEL - display buttons
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        btnView = new JButton("View Inventories");
        setButtonText(btnView);
        panelSouth.add(btnView);
        btnStop = new JButton("Exit Menu");
        setButtonText(btnStop);
        panelSouth.add(btnStop);

        summary.add(panelSouth, BorderLayout.SOUTH);
        summary.setVisible(true);

        return summary;
    }

    public JFrame displayInventories(ArrayList<String> itemNames, ArrayList<Integer> quants) {
        inventories = initFrame();
        // NORTH PANEL
        JPanel panelNorth = initNorthPanel();
        panelNorth.setBorder(new EmptyBorder(10,10,10,10));

        setHeading(lblInvent);
        panelNorth.add(lblInvent);
        inventories.add(panelNorth, BorderLayout.NORTH);

        // WEST PANEL
        JPanel panelWest = new JPanel();
        panelWest.setLayout(new GridBagLayout());
        panelWest.setBorder(new EmptyBorder(10,10,10,10));
        btnLeft = new JButton("<");
        panelWest.add(btnLeft);
        inventories.add(panelWest, BorderLayout.WEST);

        // EAST PANEL
        JPanel panelEast = new JPanel();
        panelEast.setLayout(new GridBagLayout());
        panelEast.setBorder(new EmptyBorder(10,10,10,10));
        btnRight = new JButton(">");
        panelEast.add(btnRight);
        inventories.add(panelEast, BorderLayout.EAST);

        // CENTER PANEL
        inventories.add(displayInventory(itemNames, quants), BorderLayout.CENTER);

        // SOUTH PANEL - display exit button
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        btnStop = new JButton("Exit Menu");
        setButtonText(btnStop);
        panelSouth.add(btnStop);

        inventories.add(panelSouth, BorderLayout.SOUTH);
        inventories.setVisible(true);

        return inventories;
    }

    public JPanel displayInventory(ArrayList<String> items, ArrayList<Integer> quants) {
        JPanel inventPanel = new JPanel();
        inventPanel.setLayout(new GridLayout(0,2));
        inventPanel.setBorder(new EmptyBorder(5,5,5,5));
        int i = 0;

        for (String itemDetail : items) {
            if (i < 15) {
                JLabel lblItem = new JLabel(itemDetail);
                lblItem.setForeground(Color.BLACK);
                lblItem.setFont(new Font("Verdana", Font.PLAIN, 10));

                JLabel lblQuant = new JLabel(String.valueOf(quants.get(i)));
                lblQuant.setForeground(Color.BLACK);
                lblQuant.setFont(new Font("Verdana", Font.PLAIN, 10));

                inventPanel.add(lblItem);
                inventPanel.add(lblQuant);
            }
            i++;
        }

        return inventPanel;
    }

    // Display all items in grid format
    public JPanel displayItems(ArrayList<String> items) {
        JPanel itemPanel = new JPanel();
        itemButtons = initItemButtons(items.size());
        itemPanel.setLayout(new GridLayout(0,5));
        itemPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        int i = 0;

        for (String itemDetail : items) {
            JLabel lblItem = new JLabel(itemDetail);
            lblItem.setForeground(Color.BLACK);
            lblItem.setFont(new Font("Verdana", Font.PLAIN, 10));

            if (i % 5 == 0 && i != 0)
            {
                itemPanel.add(itemButtons.get(i / 5 - 1));
                i++;
            }

            itemPanel.add(lblItem);
            i++;
        }

        return itemPanel;
    }

    // Initialize buttons to select item
    private ArrayList<JButton> initItemButtons(int slots) {
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        for (int i = 1; i <= slots; i++) {
            buttons.add(new JButton("#" + i));
        }

        return buttons;
    }

    // Initialize buttons to insert money
    private ArrayList<JButton> initMoneyButtons() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(new JButton("P50"));
        buttons.add(new JButton("P100"));
        buttons.add(new JButton("P200"));
        buttons.add(new JButton("P500"));
        buttons.add(new JButton("P1000"));
        buttons.add(new JButton("P1"));
        buttons.add(new JButton("P5"));
        buttons.add(new JButton("P10"));
        buttons.add(new JButton("P20"));

        return buttons;
    }

    // Default north panel
    private JPanel initNorthPanel() {
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        panelNorth.setBackground(Color.decode("#eede8a"));
        panelNorth.setBorder(new EmptyBorder(10, 10, 25, 10));

        return panelNorth;
    }

    // Spinner value
    public int getSpinnerValue() {
        return Integer.parseInt(s.getValue() + "");
    }

    // Action listeners per JFrame
    public void setMainActionListener(ActionListener listener) {
        btnCreate.addActionListener(listener);
        btnTest.addActionListener(listener);
        btnExit.addActionListener(listener);
    }

    public void setCreateActionListener(ActionListener listener) {
        btnRegular.addActionListener(listener);
        btnSpecial.addActionListener(listener);
        btnBack.addActionListener(listener);
    }

    public void setTestActionListener(ActionListener listener) {
        btnTestVending.addActionListener(listener);
        btnTestMaintenance.addActionListener(listener);
        btnBack.addActionListener(listener);
    }

    public void setVMFeaturesActionListener(ActionListener listener) {
        for (JButton b : moneyButtons) {
            b.addActionListener(listener);
        }

        btnMeals.addActionListener(listener); 

        for (JButton b : itemButtons) {
            b.addActionListener(listener);
        }

        btnCancel.addActionListener(listener);
    }

    public void setMaintenanceActionListener(ActionListener listener) {
        btnRestock.addActionListener(listener);
        btnSetPrice.addActionListener(listener);
        btnCollect.addActionListener(listener);
        btnReplenish.addActionListener(listener);
        btnSummary.addActionListener(listener);
        btnBack.addActionListener(listener);
    }

    public void setRestockActionListener(ActionListener listener) {
        for (JButton b : itemButtons) {
            b.addActionListener(listener);
        }

        btnStopRestock.addActionListener(listener);
    }

    public void setAddItemActionListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void setPriceActionListener(ActionListener listener) {
        for (JButton b : itemButtons) {
            b.addActionListener(listener);
        }

        btnStop.addActionListener(listener);
    }

    public void setItemPriceActionListener(ActionListener listener) {
        btnSet.addActionListener(listener);
    }

    public void setCollectActionListener(ActionListener listener) {
        for (JButton b : moneyButtons) {
            b.addActionListener(listener);
        }

        btnCancel.addActionListener(listener);
    }

    public void setReplenishActionListener(ActionListener listener) {
        for (JButton b : moneyButtons) {
            b.addActionListener(listener);
        }

        btnCancel.addActionListener(listener);
    }

    public void setSummaryActionListener(ActionListener listener) {
        btnView.addActionListener(listener);
        btnStop.addActionListener(listener);
    }

    public void setInventoriesActionListener(ActionListener listener) {
        btnStop.addActionListener(listener);
        btnLeft.addActionListener(listener);
        btnRight.addActionListener(listener);
    }

    // Reset VM Features listener
    public void resetVMFeaturesActionListener(ActionListener listener) {
        for (JButton b : moneyButtons) {
            b.removeActionListener(listener);
        }

        btnMeals.removeActionListener(listener); 

        for (JButton b : itemButtons) {
            b.removeActionListener(listener);
        }

        btnCancel.removeActionListener(listener);
    }

    public void resetRestockActionListener(ActionListener listener) {
        for (JButton b : itemButtons) {
            b.removeActionListener(listener);
        }
    }

    public void resetCollectActionListener(ActionListener listener) {
        for (JButton b : moneyButtons) {
            b.removeActionListener(listener);
        }
    }

    public void resetReplenishActionListener(ActionListener listener) {
        for (JButton b : moneyButtons) {
            b.removeActionListener(listener);
        }
    }

    // Hide JFrames
    public void hideMm() {
       mm.dispose();
    }

    public void hideCreate() {
        create.dispose();
    }

    public void hideTest() {
        test.dispose();
    }

    public void hideVendingFeatures() {
        vmFeatures.dispose();
    }

    public void hideMaintenanceMenu() {
        maintenanceFeatures.dispose();
    }

    public void hideRestock() {
        restock.dispose();
    }

    public void hideItemRestockMenu() {
        itemRestockMenu.dispose();
    }

    public void hidePrice() {
        setPrice.dispose();
    }

    public void hideItemPriceMenu() {
        itemPriceMenu.dispose();
    }

    public void hideCollect() {
        collect.dispose();
    }

    public void hideReplenish() {
        replenish.dispose();
    }

    public void hideSummary() {
        summary.dispose();
    }

    public void hideInventories() {
        inventories.dispose();
    }

    public void hidePickedItems() {
        pickedItems.dispose();
    }

    // Popup window when no VM was created yet
    public void noVMError() {
        JOptionPane.showMessageDialog(new JFrame(), "Please create a vending machine to proceed.");
    }

    // Popup window when VM is created
    public void vmCreated() {
        JOptionPane.showMessageDialog(new JFrame(), "Vending machine successfully created. Default items stocked.");
    }

    // Popup window when dispensing item
    public void dispensingItem(String name, int calories) {
        JOptionPane.showMessageDialog(new JFrame(), "1 " + name + " containing " + calories + " calories coming up...");
        JOptionPane.showMessageDialog(new JFrame(), "Dispensed!");
    }

    public void displaySteps(String steps) {
        JOptionPane.showMessageDialog(new JFrame(), steps);
    }

    public void displayDone(int calories) {
        JOptionPane.showMessageDialog(new JFrame(), "Dispensing...");
        JOptionPane.showMessageDialog(new JFrame(), "Dispensed! Contains " + calories + " calories.");
    }

    // Popup window when purchase is invalid
    public void invalidPurchase() {
        JOptionPane.showMessageDialog(new JFrame(), "Invalid purchase!");
    }

    // Popup window when the vending machine cannot produce change
    public void notEnoughMoney() {
        JOptionPane.showMessageDialog(new JFrame(), "Error: The vending machine cannot produce enough change.");
    }

    public void invalidDate() {
        JOptionPane.showMessageDialog(new JFrame(), "Error: Please enter a valid date in the format MM/dd/yyyy.");
    }

    // Popup window when dispensing change
    public void displayChange(String change) {
        JOptionPane.showMessageDialog(new JFrame(), change);
    }

    public void displayCollection(int amount) {
        JOptionPane.showMessageDialog(new JFrame(), "Collected P" + amount + " from the vending machine.");
    }

    public void invalidCollection() {
        JOptionPane.showMessageDialog(new JFrame(), "Invalid amount!");
    }

    public void displayReplenish(int amount) {
        JOptionPane.showMessageDialog(new JFrame(), "Added P" + amount + " to the vending machine.");
    }

    public void quantityAdded(String name, int amount) {
        JOptionPane.showMessageDialog(new JFrame(), amount + " quantities of " + name + " added.");
    }
    
    public void invalidAddOn() {
        JOptionPane.showMessageDialog(new JFrame(), "Invalid add-on picked.");
    }

    public boolean addAnotherItem() {
        return JOptionPane.showConfirmDialog(null, "Continue adding items?", "", JOptionPane.YES_NO_OPTION)
        == JOptionPane.YES_OPTION;
    }

    // Display user's money
    public void setDisplayMoney(int value) {
        lblMoney.setText("Your money: " + value);
    }

    public void setDisplayInventoryInitial() {
        lblInvent.setText("Starting Inventory");
    }

    public void setDisplayInventoryFinal() {
        lblInvent.setText("Final Inventory");
    }

    // Set text to heading/subheading/button
    private void setHeading(JLabel label) {
        label.setFont(new Font("Verdana", Font.BOLD, 20));
    }

    private void setSubHeading(JLabel label) {
        label.setFont(new Font("Verdana", Font.BOLD, 15));
    }

    private void setButtonText(JButton button) {
        button.setFont(new Font("Verdana", Font.BOLD, 12));
    }
}
