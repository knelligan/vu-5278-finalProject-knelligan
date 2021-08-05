package barManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.time.LocalDate;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.awt.Color;
/**
 * A simulator for a Bar Management order and inventory system
 * 
 * @author Kate Nelligan
 * @version Summer 2021
 */

public class OrderPanel implements Runnable, ActionListener{

    // the maximum number of beef patties allowed on a burger
    //private static final int MAX_PATTIES = 4;

    // what can we get on a burger?  adding items here will cause the code in run
    // to create a JCheckBox for each option and the values of the checked boxes
    // to be added to the order
    private static final int[] drinkCount = {0,1,2,3,4,5,6,7,8,9,10};

    // the components we need to remember get instance variables
    private JTextArea orderStatus;
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

    private JButton btnAdd, btnClear, btnCancel, btnUndo,btnPrint, btnAddWine, btnAddDraft, btnAddBottle, btnAddMixed, btnAddLiquor, btnAddNonAlcoholic;
    private JFrame frame;

    //instance variables
    private static String bartenderName;
    private static String customerName;
    private static int orderNumber;
    private static boolean orderedSomething;
    //private static StringBuilder appendOrder;
    private ArrayList<String> drinkNames;
    private ArrayList<Integer> drinkQuantities;
    Date orderDate;

    private static OrderDB orderDB;

    private static StringBuilder appendOrder;
    private static String currentOrder;

    private ArrayList<String> undoLast;
    @Override
    public void run() {
        drinkNames = new ArrayList();
        drinkQuantities = new ArrayList();
        undoLast = new ArrayList();
        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);

        // create a JFrame in which we will build our very
        // tiny GUI, and give the window a name
        frame = new JFrame("Bar Management");
        frame.setPreferredSize(new Dimension(800,625));

        // tell the JFrame that when someone closes the
        // window, the application should terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // a BorderLayout JPanel to occupy the frame
        JPanel framePanel = new JPanel(new BorderLayout());
        frame.add(framePanel);

        JPanel statusPanel = new JPanel();
        // a BoxLayout with the PAGE_AXIS option gives us a vertical stack
        // within this panel
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));

        // this attempt to left justify didn't completely work
        JLabel current = new JLabel("Current Order:");
        current.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(current);

        // we would like to display the order results in a large text area, but would
        // like to have scrollbars appear when needed, so we create a JScrollPane,
        // and place an uneditable JTextArea within
        JScrollPane scrollFrame = new JScrollPane();
        orderStatus = new JTextArea(10, 50);
        // we don't want the user editing the order directly - they should
        // add items here using the controls in the window.
        orderStatus.setEditable(false);
        scrollFrame.add(orderStatus);
        scrollFrame.setViewportView(orderStatus);
        statusPanel.add(scrollFrame);

        framePanel.add(statusPanel, BorderLayout.NORTH);

        // next, the main controls area where the order information is selected
        // again with a BoxLayout to get a vertical stack - here there will be a 
        // stack of JPanels each of which holds controls for one of the types of
        // items our customers can order
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.PAGE_AXIS));

        // the customer's name (required field)
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
        namePanel.add(tfDateField);
        orderPanel.add(namePanel);

        //get current date time with Date()
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        orderDate = new Date();
        tfDateField.setText(dateFormat.format(orderDate));

        namePanel.add(new JLabel("Order Number: "));
        tfOrderNumber = new JTextField("",6);
        tfOrderNumber.setText("" + orderNumber);
        namePanel.add(tfOrderNumber);

        // Wine order-------------------------------------------------------------------
        JPanel winePanel = new JPanel();

        JLabel lbWineMenu = new JLabel("Wine:");
        lbWineMenu.setHorizontalAlignment(JLabel.LEFT);

        
        winePanel.add(lbWineMenu);
        orderPanel.add(winePanel);

        wineMenu = new JComboBox();
        wineMenu.addItem("");
        wineMenu.addItem("Red wine");
        wineMenu.addItem("White wine");
        winePanel.add(wineMenu);

        wineSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        winePanel.add(wineSpinner);

        wineMenu.addActionListener(this);

        btnAddWine = new JButton("Add Wine");
        btnAddWine.addActionListener(this);
        winePanel.add(btnAddWine);

        // Draft beer order-------------------------------------------------------------
        JPanel draftBeerPanel = new JPanel();
        draftBeerPanel.add(new JLabel("Draft Beer:"));

        orderPanel.add(draftBeerPanel);

        draftMenu = new JComboBox();
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

        draftSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        draftBeerPanel.add(draftSpinner);

        draftMenu.addActionListener(this);
        draftSizeMenu.addActionListener(this);

        btnAddDraft = new JButton("Add Draft Beer");
        btnAddDraft.addActionListener(this);
        draftBeerPanel.add(btnAddDraft);

        // bottle beer order------------------------------------------------------------
        JPanel bottleBeerPanel = new JPanel();
        bottleBeerPanel.add(new JLabel("Bottle Beer:"));

        orderPanel.add(bottleBeerPanel);

        bottleMenu = new JComboBox();
        bottleMenu.addItem("");
        bottleMenu.addItem("Bud Light");
        bottleMenu.addItem("Corona");
        bottleMenu.addItem("Heineken");
        bottleBeerPanel.add(bottleMenu);

        bottleSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        bottleBeerPanel.add(bottleSpinner);

        bottleMenu.addActionListener(this);

        btnAddBottle = new JButton("Add Bottle Beer");
        btnAddBottle.addActionListener(this);
        bottleBeerPanel.add(btnAddBottle);

        // Liquor order------------------------------------------------------------------
        JPanel liquorPanel = new JPanel();
        liquorPanel.add(new JLabel("Liquor:"));

        orderPanel.add(liquorPanel);

        liquorMenu = new JComboBox();
        liquorMenu.addItem("");
        liquorMenu.addItem("Vodka Shot");
        liquorMenu.addItem("Whiskey Shot");
        liquorMenu.addItem("Tequila Shot");
        liquorMenu.addItem("Gin Shot");
        liquorMenu.addItem("Bourbon Shot");
        liquorMenu.addItem("Rum Shot");
        liquorMenu.addItem("Cordial Shot");
        liquorPanel.add(liquorMenu);

        liquorSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        liquorPanel.add(liquorSpinner);

        liquorMenu.addActionListener(this);

        btnAddLiquor = new JButton("Add Liquor");
        btnAddLiquor.addActionListener(this);
        liquorPanel.add(btnAddLiquor);

        // Mixed drink order-------------------------------------------------------------
        JPanel mixedPanel = new JPanel();
        mixedPanel.add(new JLabel("Mixed Drinks:"));

        orderPanel.add(mixedPanel);

        mixedMenu = new JComboBox();
        mixedMenu.addItem("");
        mixedMenu.addItem("Gin Martini");
        mixedMenu.addItem("Frozen Margarita");
        mixedMenu.addItem("Baybreeze");
        mixedMenu.addItem("Cosmopolitan");
        mixedMenu.addItem("Bourbon Manhattan");
        mixedMenu.addItem("Rum and Coke");
        mixedPanel.add(mixedMenu);

        mixedSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        mixedPanel.add(mixedSpinner);

        mixedMenu.addActionListener(this);

        btnAddMixed = new JButton("Add Mixed Drink");
        btnAddMixed.addActionListener(this);
        mixedPanel.add(btnAddMixed);

        // NonAlcoholic drink order------------------------------------------------------
        JPanel nonAlcoholicPanel = new JPanel();
        nonAlcoholicPanel.add(new JLabel("Non-Alcoholic Drinks:"));

        orderPanel.add(nonAlcoholicPanel);

        nonAlcoholicMenu = new JComboBox();
        nonAlcoholicMenu.addItem("");
        nonAlcoholicMenu.addItem("Coke");
        nonAlcoholicMenu.addItem("Sprite");
        nonAlcoholicMenu.addItem("Ginger Ale");
        nonAlcoholicMenu.addItem("Cranberry Juice");
        nonAlcoholicMenu.addItem("Orange Juice");
        nonAlcoholicMenu.addItem("Pineapple Juice");
        nonAlcoholicPanel.add(nonAlcoholicMenu);

        nonAlcoholicSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        nonAlcoholicPanel.add(nonAlcoholicSpinner);

        nonAlcoholicMenu.addActionListener(this);

        btnAddNonAlcoholic = new JButton("Add NA Drink");
        btnAddNonAlcoholic.addActionListener(this);
        nonAlcoholicPanel.add(btnAddNonAlcoholic);
        //-------------------------------------------------------------------------------
        // this will put a divider line between the main "ordering" controls
        // and the buttons at the bottom
        orderPanel.add(new JSeparator());

        framePanel.add(orderPanel, BorderLayout.CENTER);

        // last, the buttons to submit orders, reset, etc.
        JPanel buttonPanel = new JPanel();

        btnAdd = new JButton("Submit Order");
        btnAdd.setBackground(Color.GREEN);
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(this);
        buttonPanel.add(btnAdd);

        btnUndo = new JButton("Undo Last Entry");
        btnUndo.setBackground(Color.RED);
        btnUndo.setOpaque(true);
        btnUndo.addActionListener(this);
        buttonPanel.add(btnUndo);
        
        btnPrint= new JButton("Print Orders");
        btnPrint.setBackground(Color.YELLOW);
        btnPrint.setOpaque(true);
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
        }

        else if(e.getActionCommand().equals("Submit Order")){
            if (customerName.equals("")) {
                displayError("noName");
            }
            Order newOrder = submitOrder();
            orderDB.insert(orderNumber, newOrder);
            clearAll();
        }else{
            if (customerName.equals("")) {
                displayError("noName");
            }else{
                if (e.getActionCommand().equals("Add Wine")) {
                    quantity = getQuantity("wine");
                    if(quantity > 0){
                        drinkOrder = getOrder("wine");
                        if(!drinkOrder.equals("")){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                        }
                    }else{
                        displayError("noQuantity");
                    }
                }else if(e.getActionCommand().equals("Add Draft Beer")){
                    quantity = getQuantity("draft");
                    if(quantity > 0){
                        drinkOrder = getOrder("draft");
                        if(!drinkOrder.equals("")){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                        }
                    }else{
                        displayError("noQuantity");
                    }
                }else if(e.getActionCommand().equals("Add Bottle Beer")){
                    quantity = getQuantity("bottle");
                    if(quantity > 0){
                        drinkOrder = getOrder("bottle");
                        if(!drinkOrder.equals("")){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                        }
                    }else{
                        displayError("noQuantity");
                    }
                }else if(e.getActionCommand().equals("Add Liquor")){
                    quantity = getQuantity("liquor");
                    if(quantity > 0){
                        drinkOrder = getOrder("liquor");
                        if(!drinkOrder.equals("")){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                        }
                    }else{
                        displayError("noQuantity");
                    }
                }else if(e.getActionCommand().equals("Add Mixed Drink")){
                    quantity = getQuantity("mixed");
                    if(quantity > 0){
                        drinkOrder = getOrder("mixed");
                        if(!drinkOrder.equals("")){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
                        }
                    }else{
                        displayError("noQuantity");
                    }
                }else if(e.getActionCommand().equals("Add NA Drink")){
                    quantity = getQuantity("noAlcohol");
                    if(quantity > 0){
                        drinkOrder = getOrder("noAlcohol");
                        if(!drinkOrder.equals("")){
                            appendOrderDisplay(drinkOrder, quantity);
                            addDrink(drinkOrder, quantity);
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
        }        else if(errorType.equals("noSize")){
            JOptionPane.showMessageDialog(null, "Please select size for draft!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }        else if(errorType.equals("cantPrint")){
            JOptionPane.showMessageDialog(null, "No orders to print!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    
        public void printOrders(){
            orderDB.printAll();

    }
    public void resetMenu(){
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

    }

    public void clearAll(){
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

        bartenderName = "";
        customerName = "";
        orderNumber = orderDB.getSize()+1;
        tfOrderNumber.setText("" + orderNumber);
        tfCustomerName.setText("");
        tfBartenderName.setText("");

        drinkNames.clear();
        drinkQuantities.clear();
        undoLast.clear();
    }

    public void resetForm(){
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
        undoLast.remove(drinkNames.size()-1);


    }

    public void appendOrderDisplay(String drinkName, int quantity){  
        // appendOrder.append(""+ quantity + " ");
        // appendOrder.append(drinkName);
        // appendOrder.append("\n");
        // orderStatus.append(appendOrder.toString());
        String str = (""+ quantity + " "+drinkName+"\n");
        undoLast.add(str);

        //currentOrder = currentOrder +""+ quantity + " "+drinkName+ "\n";
        orderStatus.append(str);
    }

    public void addDrink(String drinkName, int quantity){
        drinkNames.add(drinkName);
        drinkQuantities.add(quantity);
        resetMenu();
    }

    public Order submitOrder(){

        Order newOrder = new Order(orderNumber, bartenderName,customerName, orderDate, drinkNames, drinkQuantities);
        return newOrder;
    }

    // /**
    // main method to construct our object and launch a thread
    // to run it.
    // @param args not used
    // */
    // public static void main() {

    // // The main method is responsible for creating a thread that
    // // will construct and show the graphical user interface.
    // javax.swing.SwingUtilities.invokeLater(new OrderPanel());

    // //initialize the data storage class
    // }

    /**
    main method to construct our object and launch a thread
    to run it.
    @param args not used
     */
    public static void main(String bartender, String customer, int ordNum, OrderDB odb) {
        bartenderName = bartender;
        customerName = customer;
        orderNumber = ordNum;
        orderedSomething = false;
        appendOrder = new StringBuilder();
        orderDB = odb;
        // The main method is responsible for creating a thread that
        // will construct and show the graphical user interface.
        javax.swing.SwingUtilities.invokeLater(new OrderPanel());

        //initialize the data storage class
    }

}