package barManagement;

import java.util.ArrayList;
import java.util.Date;
import java.text.NumberFormat;
/**
 * Contains all of the information used to build an order including:
 * selected drinks, customer information (tab?), number of each type of drink.
 * Calls the DrinkFactory to generate each drink.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Order
{
    private Integer orderNum;
    private String bartenderName;
    private String customerName;
    private Date date;
    private ArrayList<DrinkAndQuantity> drinksAndQuantities;
    private static DrinkFactory df;
    private static InventoryDB inventoryDB;
    private Double totalPrice;
    private boolean paid;
    private String paymentType;
    /**
     * Constructor for objects of class Order
     */
    public Order(Integer orderNum, String bartenderName, String customerName, Date date, ArrayList<String> drinkList, ArrayList<Integer>quantities, InventoryDB idb)
    {
        df = new DrinkFactory();
        inventoryDB = idb;
        this.orderNum = orderNum;
        this.bartenderName = bartenderName;
        this.customerName = customerName;
        this.date = date;
        drinksAndQuantities = new ArrayList();
        pourDrinks(drinkList, quantities);
        totalPrice = getTotalPrice();
        paid = false;
    }
    
        /**
     * Constructor for objects of class Order
     */
    public Order(Integer orderNum, String bartenderName, String customerName, Date date, boolean paid, String payType, ArrayList<String> drinkList, ArrayList<Integer>quantities, InventoryDB idb)
    {
        df = new DrinkFactory();
        inventoryDB = idb;
        this.orderNum = orderNum;
        this.bartenderName = bartenderName;
        this.customerName = customerName;
        this.date = date;
        drinksAndQuantities = new ArrayList();
        pourDrinks(drinkList, quantities);
        totalPrice = getTotalPrice();
        this.paid = paid;
        paymentType = payType;
    }

    /**
     * Method returns the order number
     *
     * @return The order number
     */
    public void pourDrinks(ArrayList<String> drinks,ArrayList<Integer> quantities)
    {

        for(int i = 0; i < quantities.size(); i++){
            DrinkAndQuantity drink = new DrinkAndQuantity();
            Drink beverage = df.makeDrink(drinks.get(i));

            //after drink is made, update the inventory
            updateInventory(beverage, quantities.get(i));

            //update DrinkAndBeverage object
            drink.setDrink(beverage);            
            drink.setQuantity(quantities.get(i));
            drinksAndQuantities.add(drink);
        }
    }

    /**
     * Method returns the order number
     *
     * @return The order number
     */
    public void updateInventory(Drink drink, int numDrinks)
    {

        String name = drink.getName();
        double size = drink.getSize();
        String type = drink.getType();
        if(drink.getType().equals("Small Draft Beer") || drink.getType().equals("Large Draft Beer")){
            //update size to account for quantities
            size = size * numDrinks;
            inventoryDB.updateQuantity(name.substring(6), size);
        }else if(drink.getType().equals("Mixed Drink")){
            MixedDrink md = (MixedDrink)(drink);            
            for(NonAlcoholic component: md.getMixer()){
                name = component.getName();
                size = component.getSize();
                //update size to account for quantities
                size = size * numDrinks;
                inventoryDB.updateQuantity(name, size);
            }
            for(Liquor component: md.getLiquor()){
                name = component.getName();
                size = component.getSize();
                //update size to account for quantities
                size = size * numDrinks;
                inventoryDB.updateQuantity(name, size);
            }
        }else{
            //update size to account for quantities
            size = size * numDrinks;
            inventoryDB.updateQuantity(name, size);
        }
    }

    /**
     * Method returns the order number
     *
     * @return The order number
     */
    public Integer getOrderNumber()
    {
        return orderNum;
    }

    /**
     * Method gets the bartender name
     *
     */
    public String getBartenderName()
    {
        return bartenderName;
    }

    /**
     * Method returns the customer name
     *
     * @return The customer name
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Method returns the date of the order
     *
     * @return The order date
     */
    public Date getDate()
    {
        return date;
    }

    public boolean getPayment()
    {
        return paid;
    }

    public void setPayment(boolean newPaid)
    {
        paid = newPaid;
    }
    
        public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String newType)
    {
        paymentType = newType;
    }

    /**
     * Method returns all of the drinks from the order
     *
     * @return The list of drinks ordered
     */
    public ArrayList<DrinkAndQuantity> getDrinks()
    {
        return drinksAndQuantities;
    }

    /**
     * Method returns a string with order information
     *
     * @return The list of drinks ordered
     */
    @Override
    public String toString()
    {
        String str = "";
        str += "\n********* Receipt *********\n";
        str += "Order Number: " + orderNum + "\nDate: " + date + "\n";
        str += "Bartender: " + bartenderName + ",  " + "Customer: " + customerName + "\n";
        str += "Drinks: ";

        for(DrinkAndQuantity d : drinksAndQuantities){
            str += d.toString() + ", ";
        }
        str = str.substring(0, str.length() - 2);
        
        //get payment info
        String payValue = "";
        String payType = "";
        if(paid){
            payValue = " Yes,";
            payType = paymentType;
        }else{
            payValue = " No,";
            payType = paymentType;
        }
        
        str += "\n";
        str += "Paid: " + payValue + " " + paymentType;
        
        //get price
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String totalCurrency = formatter.format(totalPrice);
        str += "\n";
        str += "Total: " + totalCurrency;
        str += "\n***************************\n";
        return str;
    }

    /**
     * Method returns all of the drinks from the order
     *
     * @return The list of drinks ordered
     */

    public Double getTotalPrice()
    {
        Double total = 0.0;
        for(DrinkAndQuantity d : drinksAndQuantities){
            Drink drink = d.getDrink();
            double tempPrice = drink.getPrice();
            Integer quantity = d.getQuantity();
            total += (tempPrice * quantity);
        }

        return total;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bartenderName == null) ? 0 : bartenderName.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((drinksAndQuantities == null) ? 0 : drinksAndQuantities.hashCode());
		result = prime * result + ((orderNum == null) ? 0 : orderNum.hashCode());
		result = prime * result + (paid ? 1231 : 1237);
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (bartenderName == null) {
			if (other.bartenderName != null)
				return false;
		} else if (!bartenderName.equals(other.bartenderName))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (drinksAndQuantities == null) {
			if (other.drinksAndQuantities != null)
				return false;
		} else if (!drinksAndQuantities.equals(other.drinksAndQuantities))
			return false;
		if (orderNum == null) {
			if (other.orderNum != null)
				return false;
		} else if (!orderNum.equals(other.orderNum))
			return false;
		if (paid != other.paid)
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}
    
    
    
}
