package barManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import javax.swing.DefaultComboBoxModel;
/**
 * A simulator for a Bar Management order and inventory system
 * 
 * @author Kate Nelligan
 * @version Summer 2021
 */

public class OrderPanel implements Runnable, ActionListener, ItemListener{
    private static final int[] drinkCount = {0,1,2,3,4,5,6,7,8,9,10};

    private JTextArea orderStatus;
    private JTextArea tfCurrentTotal;
    private JTextField tfBartenderName;
    private JTextField tfCustomerName;
    private JTextField tfDateField;
    private JTextField tfOrderNumber;

    private JComboBox wineMenu;
    private JComboBox nonAlcoholicMenu;
    private JComboBox draftMenu;
    private JComboBox draftSizeMenu;
    private JComboBox bottleMenu;
    private JComboBox mixedMenu;
    private JComboBox liquorMenu;
    private JComboBox openOrders;
    private DefaultComboBoxModel<String> cbModel;

    private JLabel wineLabel;
    private JLabel nonAlcoholicLabel;
    private JLabel draftLabel;
    private JLabel bottleLabel;
    private JLabel mixedLabel;    
    private JLabel liquorLabel;   

    private JSpinner wineSpinner;
    private JSpinner draftSpinner;
    private JSpinner bottleSpinner;
    private JSpinner mixedSpinner;
    private JSpinner liquorSpinner;   
    private JSpinner nonAlcoholicSpinner;

    private static final String[] payOptions = {
            "None",
            "Open Tab",
            "Cash",
            "Credit"
        };
    private JRadioButton[] paymentButtons;

    private JButton btnOpenProcessForm, btnAdd, btnClear, btnCancel, btnPrint, btnAddWine, btnAddDraft, btnAddBottle, btnAddMixed, btnAddLiquor, btnAddNonAlcoholic;
    private JFrame frame;

    //instance variables
    private static String bartenderName;
    private static String customerName;
    private static int orderNumber;
    //private static StringBuilder appendOrder;
    private ArrayList<String> drinkNames;
    private ArrayList<Integer> drinkQuantities;
    Date orderDate;

    private static OrderDB orderDB;
    private static InventoryDB inventoryDB;

    private static StringBuilder appendOrder;
    private static String currentOrder;
    private static String currentTotal;

    private ArrayList<String> undoLast;

    private static boolean paid;
    
    @Override
    public void run() {
        drinkNames = new ArrayList();
        drinkQuantities = new ArrayList();
        undoLast = new ArrayList();

        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame("Bar Management");
        frame.setPreferredSize(new Dimension(800,550));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // a BorderLayout JPanel to occupy the frame
        JPanel framePanel = new JPanel(new BorderLayout());
        frame.add(framePanel);

        //shows the current order and information

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel current = new JLabel("Current Order:");
        statusPanel.add(current);
        orderStatus = new JTextArea(2, 25);
        orderStatus.setEditable(false);
        statusPanel.add(orderStatus);

        //create a total price textfield section
        JLabel totalLabel = new JLabel("     Current Total:");
        tfCurrentTotal =  new JTextArea(1,25);

        currentTotal = "$0.00";
        tfCurrentTotal.setText(currentTotal);

        tfCurrentTotal.setEditable(false);
        statusPanel.add(totalLabel);
        statusPanel.add(tfCurrentTotal);

        framePanel.add(statusPanel, BorderLayout.NORTH);

        JPanel orderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //add a separator
        JSeparator statusSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        Dimension d = statusSeparator.getPreferredSize();
        statusSeparator.setPreferredSize(new Dimension (800,3));
        orderPanel.add(statusSeparator);        

        // the bartender's name (required field)
        JPanel namePanel = new JPanel();
        JLabel lbBartenderName = new  JLabel("Bartender Name: ");
        lbBartenderName.setHorizontalAlignment(JLabel.LEFT);
        namePanel.add(lbBartenderName);
        tfBartenderName = new JTextField("", 10);
        tfBartenderName.setText(bartenderName);
        namePanel.add(tfBartenderName);

        // the customer's name (required field)
        namePanel.add(new JLabel("Customer Name: "));
        tfCustomerName = new JTextField("", 10);
        tfCustomerName.setText(customerName);
        namePanel.add(tfCustomerName);

        // the current date
        namePanel.add(new JLabel("Date: "));
        tfDateField = new JTextField("", 12);
        tfDateField.setEditable(false);
        namePanel.add(tfDateField);
        orderPanel.add(namePanel);

        //get current date time with Date()
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        orderDate = new Date();
        tfDateField.setText(dateFormat.format(orderDate));

        namePanel.add(new JLabel("Order Number: "));
        tfOrderNumber = new JTextField("",6);
        tfOrderNumber.setText("" + orderNumber);
        tfOrderNumber.setEditable(false);
        namePanel.add(tfOrderNumber);

        //add a separator
        JSeparator nameSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        nameSeparator.setPreferredSize(new Dimension (800,3));
        orderPanel.add(nameSeparator);    

        // Wine order-------------------------------------------------------------------
        //dimensions for comboboxes and buttons
        Dimension cbDimension = new Dimension(150,25);
        Dimension btnDimension = new Dimension(140,25);
        JPanel winePanel = new JPanel();

        JLabel lbWineMenu = new JLabel("House Wine:    ");
        lbWineMenu.setHorizontalAlignment(JLabel.LEFT);

        winePanel.add(lbWineMenu);
        orderPanel.add(winePanel);

        wineMenu = new JComboBox();
        wineMenu.setPreferredSize(cbDimension);
        wineMenu.addItem("");
        wineMenu.addItem("Red wine");
        wineMenu.addItem("White wine");
        winePanel.add(wineMenu);

        JLabel lbWineNum = new JLabel("                         Number of Drinks");
        winePanel.add(lbWineNum);

        wineSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        winePanel.add(wineSpinner);

        wineMenu.addActionListener(this);

        btnAddWine = new JButton("Add Wine");
        btnAddWine.setPreferredSize(btnDimension);
        btnAddWine.addActionListener(this);
        winePanel.add(btnAddWine);

        // Draft beer order-------------------------------------------------------------
        JPanel draftBeerPanel = new JPanel();
        draftBeerPanel.add(new JLabel("Draft Beer:       "));

        orderPanel.add(draftBeerPanel);

        draftMenu = new JComboBox();
        draftMenu.setPreferredSize(cbDimension);
        draftMenu.addItem("");
        draftMenu.addItem("Coors Light");
        draftMenu.addItem("Sam Adams");
        draftMenu.addItem("Guiness");
        draftBeerPanel.add(draftMenu);

        draftSizeMenu = new JComboBox();
        draftSizeMenu.addItem("");
        draftSizeMenu.addItem("Small");
        draftSizeMenu.addItem("Large");
        draftBeerPanel.add(draftSizeMenu);

        JLabel lbBeerNum = new JLabel("      Number of Drinks");
        draftBeerPanel.add(lbBeerNum);

        draftSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        draftBeerPanel.add(draftSpinner);

        draftMenu.addActionListener(this);
        draftSizeMenu.addActionListener(this);

        btnAddDraft = new JButton("Add Draft Beer");
        btnAddDraft.setPreferredSize(btnDimension);
        btnAddDraft.addActionListener(this);
        draftBeerPanel.add(btnAddDraft);

        // bottle beer order------------------------------------------------------------
        JPanel bottleBeerPanel = new JPanel();
        bottleBeerPanel.add(new JLabel("Bottle Beer:     "));

        orderPanel.add(bottleBeerPanel);

        bottleMenu = new JComboBox();
        bottleMenu.setPreferredSize(cbDimension);
        bottleMenu.addItem("");
        bottleMenu.addItem("Bud Light");
        bottleMenu.addItem("Corona");
        bottleMenu.addItem("Heineken");
        bottleBeerPanel.add(bottleMenu);

        JLabel lbBottleNum = new JLabel("                         Number of Drinks");
        bottleBeerPanel.add(lbBottleNum);

        bottleSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        bottleBeerPanel.add(bottleSpinner);

        bottleMenu.addActionListener(this);

        btnAddBottle = new JButton("Add Bottle Beer");
        btnAddBottle.setPreferredSize(btnDimension);
        btnAddBottle.addActionListener(this);
        bottleBeerPanel.add(btnAddBottle);

        // Liquor order------------------------------------------------------------------
        JPanel liquorPanel = new JPanel();
        liquorPanel.add(new JLabel("Liquor Shots:  "));

        orderPanel.add(liquorPanel);

        liquorMenu = new JComboBox();
        liquorMenu.setPreferredSize(cbDimension);
        liquorMenu.addItem("");
        liquorMenu.addItem("Vodka");
        liquorMenu.addItem("Whiskey");
        liquorMenu.addItem("Tequila");
        liquorMenu.addItem("Gin");
        liquorMenu.addItem("Bourbon");
        liquorMenu.addItem("Rum");
        liquorMenu.addItem("Cordial");
        liquorPanel.add(liquorMenu);

        JLabel lbLiquorNum = new JLabel("                         Number of Drinks");
        liquorPanel.add(lbLiquorNum);

        liquorSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        liquorPanel.add(liquorSpinner);

        liquorMenu.addActionListener(this);

        btnAddLiquor = new JButton("Add Liquor");
        btnAddLiquor.setPreferredSize(btnDimension);
        btnAddLiquor.addActionListener(this);
        liquorPanel.add(btnAddLiquor);

        // Mixed drink order-------------------------------------------------------------
        JPanel mixedPanel = new JPanel();
        mixedPanel.add(new JLabel("Mixed Drinks:  "));

        orderPanel.add(mixedPanel);

        mixedMenu = new JComboBox();
        mixedMenu.setPreferredSize(cbDimension);
        mixedMenu.addItem("");
        mixedMenu.addItem("Gin Martini");
        mixedMenu.addItem("Frozen Margarita");
        mixedMenu.addItem("Baybreeze");
        mixedMenu.addItem("Cosmopolitan");
        mixedMenu.addItem("Bourbon Manhattan");
        mixedMenu.addItem("Rum and Coke");
        mixedPanel.add(mixedMenu);

        JLabel lbMixedNum = new JLabel("                         Number of Drinks");
        mixedPanel.add(lbMixedNum);

        mixedSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        mixedPanel.add(mixedSpinner);

        mixedMenu.addActionListener(this);

        btnAddMixed = new JButton("Add Mixed Drink");
        btnAddMixed.setPreferredSize(btnDimension);
        btnAddMixed.addActionListener(this);
        mixedPanel.add(btnAddMixed);

        // NonAlcoholic drink order------------------------------------------------------
        JPanel nonAlcoholicPanel = new JPanel();
        nonAlcoholicPanel.add(new JLabel("Non-Alcoholic:"));

        orderPanel.add(nonAlcoholicPanel);

        nonAlcoholicMenu = new JComboBox();
        nonAlcoholicMenu.setPreferredSize(cbDimension);
        nonAlcoholicMenu.addItem("");
        nonAlcoholicMenu.addItem("Coke");
        nonAlcoholicMenu.addItem("Sprite");
        nonAlcoholicMenu.addItem("Ginger Ale");
        nonAlcoholicMenu.addItem("Cranberry Juice");
        nonAlcoholicMenu.addItem("Orange Juice");
        nonAlcoholicMenu.addItem("Pineapple Juice");
        nonAlcoholicPanel.add(nonAlcoholicMenu);

        JLabel lbNaNum = new JLabel("                         Number of Drinks");
        nonAlcoholicPanel.add(lbNaNum);

        nonAlcoholicSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        nonAlcoholicPanel.add(nonAlcoholicSpinner);

        nonAlcoholicMenu.addActionListener(this);

        btnAddNonAlcoholic = new JButton("Add NA Drink");
        btnAddNonAlcoholic.setPreferredSize(btnDimension);
        btnAddNonAlcoholic.addActionListener(this);
        nonAlcoholicPanel.add(btnAddNonAlcoholic);

        //add a separator
        JSeparator menuSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        menuSeparator.setPreferredSize(new Dimension (800,3));
        orderPanel.add(menuSeparator);  

        //Radio buttons for payment-------------------------------

        JPanel paymentPanel = new JPanel();
        paymentPanel.add(new JLabel("Payment Status:"));
        orderPanel.add(paymentPanel);

        ButtonGroup paymentRadio = new ButtonGroup();
        paymentButtons = new JRadioButton[payOptions.length];

        for (int size = 0; size < payOptions.length; size++) {
            paymentButtons[size] = new JRadioButton(payOptions[size]);
            paymentRadio.add(paymentButtons[size]);
            paymentPanel.add(paymentButtons[size]);
        }
        paymentButtons[0].setSelected(true);
        paymentButtons[0].addItemListener(this);

        //add a separator
        JSeparator paySeparator = new JSeparator(SwingConstants.HORIZONTAL);
        paySeparator.setPreferredSize(new Dimension (800,3));
        orderPanel.add(paySeparator);    


        //-------------------------------------------------------------------------------
        framePanel.add(orderPanel, BorderLayout.CENTER);

        // buttons to submit orders, reset, etc.
        JPanel buttonPanel = new JPanel();

        btnAdd = new JButton("Submit Order");
        btnAdd.setBackground(Color.GREEN);
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(this);
        buttonPanel.add(btnAdd);

        btnOpenProcessForm = new JButton("Process Open Orders");
        btnOpenProcessForm.addActionListener(this);
        buttonPanel.add(btnOpenProcessForm);


        btnPrint= new JButton("Print Orders");
        //btnPrint.setBackground(Color.YELLOW);
        //btnPrint.setOpaque(true);
        btnPrint.addActionListener(this);
        buttonPanel.add(btnPrint);

        btnClear = new JButton("Clear Order");
        btnClear.addActionListener(this);
        buttonPanel.add(btnClear);

        btnCancel = new JButton("Cancel Order");
        btnCancel.addActionListener(this);
        buttonPanel.add(btnCancel);

        framePanel.add(buttonPanel, BorderLayout.SOUTH);
        // display the window we've created
        frame.pack();
        frame.setVisible(true);
    }

    /**
    Method to be called when buttons are pressed or a JComboBox
    selection changes.
    Satisfies the ActionListener interface
    @param e the ActionEvent that triggered the method call
     */
    public void actionPerformed(ActionEvent e) {
        int quantity = 0;
        String drinkOrder = "";
        String priceTotal = "";
        customerName = tfCustomerName.getText();
        bartenderName = tfBartenderName.getText();
        String ord = tfOrderNumber.getText();
        orderNumber = Integer.parseInt(ord);

        if(e.getActionCommand().equals("Clear Order")){
            if(!orderStatus.getText().equals("")){
                resetForm();
            }
        }

        else if(e.getActionCommand().equals("Undo Last Entry")){
            if(undoLast.size() <= 0){
                displayError("noOrders");
            }else{
                undo();
            }
        }        else if(e.getActionCommand().equals("Print Orders")){
            if(orderDB.getSize() <= 0){
                displayError("cantPrint");
            }else{
                printOrders();
            }
        }

        else if(e.getActionCommand().equals("Cancel Order")){
            closeForm();
        }else if(e.getActionCommand().equals("Process Open Orders")){
                        ProcessOrders po = new ProcessOrders();
            po.main(orderDB);
            

        } else if(e.getActionCommand().equals("Submit Order")){
            if (customerName.equals("")) {
                displayError("noName");
            }else{
                String option = "";

                String testval = payOptions[0];
                if(!paymentButtons[0].isSelected()){

                    if(paymentButtons[2].isSelected() || paymentButtons[3].isSelected()){
                        paid = true;
                    }
                    for (int size = 1; size < payOptions.length; size++) {
                        if (paymentButtons[size].isSelected()) {
                            option = payOptions[size];
                        }
                    }

                    Order newOrder = submitOrder(option);
                    orderDB.insert(orderNumber, newOrder);
                    clearAll();
                }else{
                    displayError("noPayment");
                }
            }
        }else{
            // if (customerName.equals("")) {
            // displayError("noName");
            // }else{
            if (e.getActionCommand().equals("Add Wine")) {
                quantity = getQuantity("wine");
                if(quantity > 0){
                    drinkOrder = getOrder("wine");
                    if(!drinkOrder.equals("")){
                        //calculate amount used for drink

                        if(inventoryDB.checkStock(drinkOrder, 5.0)){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                            updatePriceTotal();
                        }else{
                            displayError("noInventory");
                        }
                    }
                }else{
                    displayError("noQuantity");
                }
            }else if(e.getActionCommand().equals("Add Draft Beer")){
                quantity = getQuantity("draft");
                if(quantity > 0){
                    drinkOrder = getOrder("draft");
                    if(!drinkOrder.equals("")){
                        //check amount of either small or large draft
                        String str = drinkOrder.substring(0,6);
                        double amt = 0.0;
                        if(str.equals("Small")){
                            amt = 12.0;
                        }else{
                            amt = 20.0;
                        }
                        if(inventoryDB.checkStock(drinkOrder.substring(6), amt)){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                            updatePriceTotal();
                        }else{
                            displayError("noInventory");
                        }
                    }
                }else{
                    displayError("noQuantity");
                }
            }else if(e.getActionCommand().equals("Add Bottle Beer")){
                quantity = getQuantity("bottle");
                if(quantity > 0){
                    drinkOrder = getOrder("bottle");
                    if(!drinkOrder.equals("")){
                        if(inventoryDB.checkStock(drinkOrder, 12.0)){                        
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                            updatePriceTotal();
                        }else{
                            displayError("noInventory");
                        }
                    }
                }else{
                    displayError("noQuantity");
                }
            }else if(e.getActionCommand().equals("Add Liquor")){
                quantity = getQuantity("liquor");
                if(quantity > 0){
                    drinkOrder = getOrder("liquor");
                    if(!drinkOrder.equals("")){
                        if(inventoryDB.checkStock(drinkOrder, 1.5)){    
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                            updatePriceTotal();
                        }else{
                            displayError("noInventory");
                        }
                    }
                }else{
                    displayError("noQuantity");
                }
            }else if(e.getActionCommand().equals("Add Mixed Drink")){
                quantity = getQuantity("mixed");
                if(quantity > 0){
                    drinkOrder = getOrder("mixed");
                    if(!drinkOrder.equals("")){
                        if(getRecipe(drinkOrder, quantity)){    
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                            updatePriceTotal();
                        }else{
                            displayError("noInventory");
                        }
                    }
                }else{
                    displayError("noQuantity");
                }
            }else if(e.getActionCommand().equals("Add NA Drink")){
                quantity = getQuantity("noAlcohol");
                if(quantity > 0){
                    drinkOrder = getOrder("noAlcohol");
                    if(!drinkOrder.equals("")){
                        if(inventoryDB.checkStock(drinkOrder, 6.0)){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                            updatePriceTotal();
                        }else{
                            displayError("noInventory");
                        }
                    }else{
                        displayError("noQuantity");
                    }
                }
            }
        }
    }

    public String getOrder(String drinkType){
        String drinkOrder = "";
        if(drinkType.equals("wine")){
            drinkOrder = (String)(wineMenu.getSelectedItem());
        }else if(drinkType.equals("draft")){
            if(draftSizeMenu.getSelectedItem().equals("")){
                displayError("noSize");
            }else{
                drinkOrder += (String)(draftSizeMenu.getSelectedItem());
                drinkOrder += " ";
                drinkOrder += (String)(draftMenu.getSelectedItem());
            }
        }else if(drinkType.equals("bottle")){
            drinkOrder = (String)(bottleMenu.getSelectedItem());
        }else if(drinkType.equals("liquor")){
            drinkOrder = (String)(liquorMenu.getSelectedItem());
        }else if(drinkType.equals("mixed")){
            drinkOrder = (String)(mixedMenu.getSelectedItem());
        }else if(drinkType.equals("noAlcohol")){
            drinkOrder = (String)(nonAlcoholicMenu.getSelectedItem());
        }
        return drinkOrder;
    }

    public int getQuantity(String drinkType){
        int quantity = 0;
        if(drinkType.equals("wine")){
            quantity = (Integer)wineSpinner.getValue();           
        }else if(drinkType.equals("draft")){
            quantity = (Integer)draftSpinner.getValue();           
        }else if(drinkType.equals("bottle")){
            quantity = (Integer)bottleSpinner.getValue();           
        }else if(drinkType.equals("liquor")){
            quantity = (Integer)liquorSpinner.getValue();           
        }else if(drinkType.equals("mixed")){
            quantity = (Integer)mixedSpinner.getValue();           
        }else if(drinkType.equals("noAlcohol")){
            quantity = (Integer)nonAlcoholicSpinner.getValue();           
        }
        return quantity;
    }

    public boolean updateInventory(String drinkType, double amount){
        boolean updated = false;
        updated = inventoryDB.updateQuantity(drinkType, amount);   
        return updated;
    }

    public void displayError(String errorType){
        if(errorType.equals("noName")){
            JOptionPane.showMessageDialog(null, "Customer name is required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("noQuantity")){
            JOptionPane.showMessageDialog(null, "No drink quantity selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("noDrink")){
            JOptionPane.showMessageDialog(null, "No drink selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(errorType.equals("noOrders")){
            JOptionPane.showMessageDialog(null, "No items entered to undo!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("noSize")){
            JOptionPane.showMessageDialog(null, "Please select size for draft!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("cantPrint")){
            JOptionPane.showMessageDialog(null, "No orders to print!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("noInventory")){
            JOptionPane.showMessageDialog(null, "Not enough inventory to produce drink", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("noPayment")){
            JOptionPane.showMessageDialog(null, "No payment type selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    public void printOrders(){
        orderDB.printAll();

    }

    public void updatePriceTotal(){
        String priceText = currentTotal;
        double currPrice = 0.00;
        double tempPrice = 0.00;

        priceText = priceText.substring(1);
        currPrice = Double.parseDouble(priceText);

        for(int i = 0; i < drinkNames.size(); i++){
            String drink = drinkNames.get(i);
            int quantities = drinkQuantities.get(i);
            tempPrice += (inventoryDB.getDrinkPrice(drink) * quantities);

        }
        tempPrice += currPrice;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String totalCurrency = formatter.format(tempPrice);
        currentTotal = totalCurrency;
        tfCurrentTotal.setText(currentTotal);
    }

    public void resetMenu(){
        paid = false;
        currentTotal = "$0.00";
        wineMenu.setSelectedItem("");
        nonAlcoholicMenu.setSelectedItem("");
        draftMenu.setSelectedItem("");
        draftSizeMenu.setSelectedItem("");
        bottleMenu.setSelectedItem("");
        mixedMenu.setSelectedItem("");
        liquorMenu.setSelectedItem("");
        tfCurrentTotal.setText(currentTotal); 

        wineSpinner.setValue(0);
        draftSpinner.setValue(0);
        bottleSpinner.setValue(0);
        mixedSpinner.setValue(0);
        liquorSpinner.setValue(0);   
        nonAlcoholicSpinner.setValue(0);
        paymentButtons[0].setSelected(true);
    }

    public void clearAll(){
        paid = false;
        currentTotal = "$0.00";
        wineMenu.setSelectedItem("");
        nonAlcoholicMenu.setSelectedItem("");
        draftMenu.setSelectedItem("");
        draftSizeMenu.setSelectedItem("");
        bottleMenu.setSelectedItem("");
        mixedMenu.setSelectedItem("");
        liquorMenu.setSelectedItem("");

        wineSpinner.setValue(0);
        draftSpinner.setValue(0);
        bottleSpinner.setValue(0);
        mixedSpinner.setValue(0);
        liquorSpinner.setValue(0);   
        nonAlcoholicSpinner.setValue(0);

        orderStatus.setText(""); 
        tfCurrentTotal.setText(currentTotal); 

        bartenderName = "";
        customerName = "";
        orderNumber = orderDB.getSize()+1;
        tfOrderNumber.setText("" + orderNumber);
        tfCustomerName.setText("");
        tfBartenderName.setText("");

        drinkNames.clear();
        drinkQuantities.clear();

        paymentButtons[0].setSelected(true);
        
    }

    public void resetForm(){
        paid = false;
        currentTotal = "$0.00";
        wineMenu.setSelectedItem("");
        nonAlcoholicMenu.setSelectedItem("");
        draftMenu.setSelectedItem("");
        draftSizeMenu.setSelectedItem("");
        bottleMenu.setSelectedItem("");
        mixedMenu.setSelectedItem("");
        liquorMenu.setSelectedItem("");

        wineSpinner.setValue(0);
        draftSpinner.setValue(0);
        bottleSpinner.setValue(0);
        mixedSpinner.setValue(0);
        liquorSpinner.setValue(0);   
        nonAlcoholicSpinner.setValue(0);

        orderStatus.setText("");
        tfCurrentTotal.setText(currentTotal); 
        paymentButtons[0].setSelected(true);
    }

    public void closeForm(){
        frame.dispose();   
    }

    public void undo(){

        String str = "";
        for(int i = 0; i < undoLast.size()-1;i++){
            str += undoLast.get(i);
        }
        orderStatus.setText(str);
        drinkNames.remove(drinkNames.size()-1);
        drinkQuantities.remove(drinkNames.size()-1);
        updatePriceTotal();
        undoLast.remove(drinkNames.size()-1);

    }

    public void appendOrderDisplay(String drinkName, int quantity){  
        String str = (""+ quantity + " "+drinkName+"\n");
        undoLast.add(str);

        orderStatus.append(str);
    }

    public void addDrink(String drinkName, int quantity){         

        //update parallel arraylists that stores drink names and quantities
        drinkNames.add(drinkName);
        drinkQuantities.add(quantity);
        
        //updatePriceTotal();
        resetMenu();
    }

    public Order submitOrder(String payType){
        Order newOrder = new Order(orderNumber, bartenderName,customerName, orderDate, paid, payType, drinkNames, drinkQuantities, inventoryDB);

        return newOrder;
    }



    public boolean getRecipe(String name, int quantity){
        boolean inStock = true;    
        MixedDrinkBuilder mdb = new MixedDrinkBuilder(name);
        Mixologist mixologist = new Mixologist(mdb);
        mixologist.makeDrink(name);

        MixedDrink md = mixologist.getDrink();
        int i = 0;

        while(i < md.getMixer().size() && inStock){
            NonAlcoholic component = md.getMixer().get(i);
            String drinkName = component.getName();
            double amount = component.getSize();
            //multiply by quantity for total amount required
            amount = amount * quantity;
            inStock = inventoryDB.checkStock(drinkName, amount);
            i++;
        }
        i = 0;
        while(i < md.getLiquor().size() && inStock){
            Liquor component = md.getLiquor().get(i);
            String drinkName = component.getName();
            double amount  = component.getSize();
            //multiply by quantity for total amount required
            amount = amount * quantity;
            inStock = inventoryDB.checkStock(drinkName, amount);
            i++;
        }

        return inStock;
    }

    /**
    Method to be called when the state of radio button changes.
    Satisfies the ItemListener interface.
    @param e the ItemEvent that trigged the method call
     */
    public void itemStateChanged(ItemEvent e) {

    }

    /**
    main method to construct our object and launch a thread
    to run it.
    @param args not used
     */
    public static void main(OrderDB odb, InventoryDB idb) {
        bartenderName = "";
        customerName = "";
        orderNumber = 1;
        paid = false;
        appendOrder = new StringBuilder();
        orderDB = odb;
        inventoryDB = idb;
        // The main method is responsible for creating a thread that
        // will construct and show the graphical user interface.
        javax.swing.SwingUtilities.invokeLater(new OrderPanel());

        //initialize the data storage class
    }

}
    