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
/**
 * A simulator for a Bar Management order and inventory system
 * 
 * @author Kate Nelligan
 * @version Summer 2021
 */

public class Dashboard implements Runnable, ActionListener{
    JFrame frame;

    // the components we need to remember get instance variables
    private JTextField tfBartenderName;
    private JTextField tfCustomerName;
    private JTextField tfOrderNumber;
    private JTextField tfDateField;

    private JButton btnOrder, btnCancel, btnClear;

    private static OrderDB orderDB;
    @Override
    public void run() {
        // set up the GUI "look and feel" which should match
        // the OS on which we are running
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Order Dashboard");
        frame.setPreferredSize(new Dimension(500,225));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel framePanel = new JPanel(new BorderLayout());
        frame.add(framePanel);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.PAGE_AXIS));
        JPanel instructionsPanel = new JPanel();
        orderPanel.add(instructionsPanel);
        JLabel instructions = new JLabel("Fill out the form below to begin order");
        instructionsPanel.add(instructions);

        // the bartender's name (required field)
        JPanel infoPanel = new JPanel();
        infoPanel.add(new JLabel("Bartender Name: "));
        tfBartenderName = new JTextField("", 15);
        infoPanel.add(tfBartenderName);

        // the orderNum (required field)

        infoPanel.add(new JLabel("Order Number: "));
        tfOrderNumber = new JTextField("", 6);
        tfOrderNumber.setText("" + (orderDB.getSize() + 1));
        infoPanel.add(tfOrderNumber);

        // the customer's name (required field)
        infoPanel.add(new JLabel("Customer Name: "));
        tfCustomerName = new JTextField("", 15);
        infoPanel.add(tfCustomerName);

        // the current date
        infoPanel.add(new JLabel("Date: "));
        tfDateField = new JTextField("", 15);
        infoPanel.add(tfDateField);
        orderPanel.add(infoPanel);

        //get current date time with Date()
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        tfDateField.setText(dateFormat.format(date));

        orderPanel.add(new JSeparator());
        framePanel.add(orderPanel, BorderLayout.CENTER);
        // last, the buttons to submit orders, reset, etc.
        JPanel buttonPanel = new JPanel();

        btnOrder = new JButton("Begin Order");
        btnOrder.addActionListener(this);
        buttonPanel.add(btnOrder);

        btnClear = new JButton("Clear Form");
        btnClear.addActionListener(this);
        buttonPanel.add(btnClear);

        btnCancel = new JButton("Close Program");
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

        //extract this out of this part of the program later.
        if (e.getActionCommand().equals("Close Program")) {
            frame.dispose();

        }else if(e.getActionCommand().equals("Clear Form")){
            tfBartenderName.setText("");
            tfCustomerName.setText("");
            int ordVal = orderDB.getSize()+1;

            tfOrderNumber.setText("" + ordVal);
        }else{
            OrderPanel orderPanel = new OrderPanel();
            orderPanel.main(getUserName(), getCustomerName(), getOrderNumber(), orderDB);
            //clearForm();
        }
    }

    public String getUserName() {
        return tfBartenderName.getText().trim();
    }

    public void clearForm() {
        tfOrderNumber.setText("" + (orderDB.getSize() + 1));
        tfBartenderName.setText("");
        tfCustomerName.setText("");

    }

    public String getCustomerName() {
        return tfCustomerName.getText().trim();
    }

    public int getOrderNumber() {
        String ordNum = tfOrderNumber.getText().trim();
        return Integer.parseInt(ordNum);
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
        javax.swing.SwingUtilities.invokeLater(new Dashboard());

        //initialize the data storage class
    }
}