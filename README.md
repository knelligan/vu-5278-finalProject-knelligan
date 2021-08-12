# vu-5278-finalProject-knelligan


Bar Management is a Java program that functions as an ordering system for a bar.  This is meant to be an end-user application to support ordering drinks at a bar establishment and stores all of the orders from the bar which can then be printed to the console.

## Running the Program

The program was created in Eclipse and runs by clicking "Run Main" on the src folder.

## Usage

**Order Form**
* Now, select from a dropdown/combo box menu of a beverage type, select a drink from that part of the menu, and click the spinner object (maximum drinks of 10) to select the quantity of that drinks from that part of the menu. Finally, click "Add [beverage type]" to add it to the current order display.

* After all of the drinks for this order are selected, choose the payment method using the radio buttons.

* Once a type of payment is selected, click the green **Submit Order** button.

* The inventory system updates the csv file and prints out usage information as each drink is processed.

* After the drinks from the order are submitted, it is then stored in a database object for later reference.

* After at least one drink order is submitted, the user can then click on the **Print Orders** button to print all of the orders in the database to the console.

* If an customer does not pay when the drinks are ordered the bartender has the option to retrieve orders based on order number. This is accomplished by clicking the **Process Orders** button which opens up a new window. 

* The combo box at the top of this window stores any order with an open tab. It is dynamically set so that after a payment is made, it is no longer selectable from the combo box. When no open orders exist, the combo box says "No orders".  To process payment, select the order from the combo box and click the **Display Order** button. Then click on the appropriate radio button for payment (cash or credit). After the payment method is selected, click **Submit Payment** to complete the payment process.

* After an order is submitted, the bartender name and customer name will be cleared as well as the current order text field.  The user will need to enter new bartender/customer names to process the next order.


* Click close program to end the program.




## To Do
- [ ] create an authentication/login
- [ ] create a reporting window to print sales, costs, and other stats
- [ ] include a command pattern design for the GUI controls
- [ ] create tests and sample data
- [x] add an inventory system 
- [x] add an "export to csv" feature (overwrites inventory csv automatically)
- [x] get user controls to function properly


