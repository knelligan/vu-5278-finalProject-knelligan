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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import javax.swing.DefaultComboBoxModel;

/**
 * A simulator for a Bar Management order and inventory system
 * 
 * @author Kate Nelligan
 * @version Summer 2021
 */

public class ProcessOrders implements Runnable, ActionListener, ItemListener{
    JFrame frame;

    // the components we need to remember get instance variables
    private JTextArea orderStatus;
    private JTextField tfBartenderName;
    private JTextField tfCustomerName;
    private JTextField tfOrderNumber;
    private JTextField tfDateField;
    private JTextField tfPrice;

    private JComboBox openOrders;
    private DefaultComboBoxModel<String> cbModel;

    // payment options
    private static final String[] payOptions = {
            "Open Tab",
            "Cash",
            "Credit"
        };
    private JRadioButton[] paymentButtons;

    private JButton btnShowOrder, btnSubmit, btnCancel, btnClear;

    private static OrderDB orderDB;

      private static boolean paid;

    @Override
    public void run() {
        //button and combo box dimensions
        Dimension cbDimension = new Dimension(150,25);

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Process Orders");
        frame.setPreferredSize(new Dimension(450,525));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel framePanel = new JPanel(new BorderLayout());
        frame.add(framePanel);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel instructionsPanel = new JPanel();
        orderPanel.add(instructionsPanel);
        JLabel instructions = new JLabel("Process open orders by selecting from the box below:");
        instructions.setHorizontalAlignment(JLabel.LEFT);
        instructionsPanel.add(instructions);

        //Process Open Orders-------------------------------
        //sets up a model to dynamically change combo box

        String[] openOrderCB = new String[]{"No orders"};
        cbModel = new DefaultComboBoxModel<>(openOrderCB);

        JPanel openOrderPanel = new JPanel();
        openOrderPanel.add(new JLabel("Open Orders:"));

        //sets the model to the combo box
        openOrders = new JComboBox();
        openOrders.setModel(cbModel);
        openOrderPanel.add(openOrders);
        updateOpenOrderCB();
        openOrders.setPreferredSize(cbDimension);
        orderPanel.add(openOrderPanel);

        //shows the order info for item selectd in combo box
        btnShowOrder = new JButton("Display Order");
        btnShowOrder.addActionListener(this);
        openOrderPanel.add(btnShowOrder);

        //add a separator
        JSeparator orderSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        orderSeparator.setPreferredSize(new Dimension (500,3));
        orderPanel.add(orderSeparator);    

        //Fields relating to selected order-------------------------------
        // the orderNum (required field)--------------------------------------
        JPanel ordNumPanel = new JPanel();       
        ordNumPanel.add(new JLabel("Order Number:     "));
        tfOrderNumber = new JTextField("", 20);
        //tfOrderNumber.setText("" + (orderDB.getSize() + 1));
        ordNumPanel.add(tfOrderNumber);
        orderPanel.add(ordNumPanel);

        // the bartender's name (required field)--------------------------------------
        JPanel bartenderPanel = new JPanel();
        bartenderPanel.add(new JLabel("Bartender Name: "));
        tfBartenderName = new JTextField("", 20);
        bartenderPanel.add(tfBartenderName);
        orderPanel.add(bartenderPanel);

        // the customer's name (required field)--------------------------------------
        JPanel customerPanel = new JPanel();
        customerPanel.add(new JLabel("Customer Name: "));
        tfCustomerName = new JTextField("", 20);
        customerPanel.add(tfCustomerName);
        orderPanel.add(customerPanel);

        // the current date--------------------------------------
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Date:            "));
        tfDateField = new JTextField("", 20);
        datePanel.add(tfDateField);
        orderPanel.add(datePanel);

        // //get current date time with Date()--------------------------------------
        // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        // Date date = new Date();
        // tfDateField.setText(dateFormat.format(date));

        // the current date
        JPanel pricePanel = new JPanel();
        pricePanel.add(new JLabel("Price:           "));
        tfPrice = new JTextField("", 20);
        pricePanel.add(tfPrice);
        orderPanel.add(pricePanel);

        //add a separator
        JSeparator paySeparator = new JSeparator(SwingConstants.HORIZONTAL);
        paySeparator.setPreferredSize(new Dimension (800,3));
        orderPanel.add(paySeparator);   

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
        JSeparator buttonSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        buttonSeparator.setPreferredSize(new Dimension (500,3));
        orderPanel.add(buttonSeparator); 

        //add buttons to control processing and closing/reseting the form

        JPanel buttonPanel = new JPanel();
        btnSubmit = new JButton("Submit Payment");
        btnSubmit.addActionListener(this);
        buttonPanel.add(btnSubmit);

        btnClear = new JButton("Clear Form");
        btnClear.addActionListener(this);
        buttonPanel.add(btnClear);

        btnCancel = new JButton("Close Window");
        btnCancel.addActionListener(this);
        buttonPanel.add(btnCancel);
        //add the main order panel to the frame panel
        framePanel.add(orderPanel);

        framePanel.add(buttonPanel, BorderLayout.SOUTH);

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

        //extract this out of this part of the program later.
        if (e.getActionCommand().equals("Close Window")) {
            frame.dispose();

        }else if(e.getActionCommand().equals("Clear Form")){
            clearTextFields();
        }else if(e.getActionCommand().equals("Display Order")){
            int selectedOrder = getOrderCB();

            updateTextFields(selectedOrder);
        }else if(e.getActionCommand().equals("Submit Payment")){
            String option = "";

            if(!paymentButtons[0].isSelected()){
                paid = true;
                for (int size = 1; size < payOptions.length; size++) {
                    if (paymentButtons[size].isSelected()) {
                        option = payOptions[size];
                    }
                }
                //get the selected order that was updated
                int selectedOrder = getOrderCB();

                //update payment
                orderDB.updatePayment(selectedOrder, true, option);
                //update the combo box
                updateOpenOrderCB();
                clearTextFields();
            }else{
                displayError("noPayment");
            }
        }
    }

    public int getOrderCB(){
        //get order number info from the combo box
        String ord = (String)(openOrders.getSelectedItem());
        String ordNum = ord.substring(1);
        int orderNumberValue = Integer.parseInt(ordNum);

        return orderNumberValue;

    }

    public void updateOpenOrderCB(){
        String[] openOrderCB = orderDB.getOpenOrders();
        if(openOrderCB.length <= 0){
            openOrderCB = new String[]{"No orders"};
        }
        cbModel = new DefaultComboBoxModel<>(openOrderCB);
        openOrders.setModel(cbModel);

    }

    public void displayError(String errorType){
        if(errorType.equals("noPayment")){
            JOptionPane.showMessageDialog(null, "No payment type selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(errorType.equals("noOrders")){
            JOptionPane.showMessageDialog(null, "No open order selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;  
        }
    }

    public void updateTextFields(int ordNum){
        Order ord = orderDB.getOrder(ordNum);
        //orderStatus.setText();
        tfBartenderName.setText(ord.getBartenderName());
        tfCustomerName.setText(ord.getCustomerName());
        tfOrderNumber.setText("" + ord.getOrderNumber());
        tfDateField.setText(ord.getDate().toString());

        //currency format
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String totalCurrency = formatter.format(ord.getTotalPrice());

        tfPrice.setText(totalCurrency);
    }

    public void clearTextFields(){
        tfBartenderName.setText("");
        tfCustomerName.setText("");
        tfOrderNumber.setText("");
        tfDateField.setText("");
        tfPrice.setText("");
        paymentButtons[0].setSelected(true);
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
    public static void main(OrderDB odb) {
        orderDB = odb;

        // The main method is responsible for creating a thread that
        // will construct and show the graphical user interface.
        javax.swing.SwingUtilities.invokeLater(new ProcessOrders());

        //initialize the data storage class
    }
}