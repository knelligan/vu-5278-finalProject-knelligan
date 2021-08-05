package barManagement;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;
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
    /**
     * Constructor for objects of class Order
     */
    public Order(Integer orderNum, String bartenderName, String customerName, Date date, ArrayList<String> drinkList, ArrayList<Integer>quantities)
    {
        df = new DrinkFactory();
        this.orderNum = orderNum;
        this.bartenderName = bartenderName;
        this.customerName = customerName;
        this.date = date;
        drinksAndQuantities = new ArrayList();
        pourDrinks(drinkList, quantities);
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
    public Integer getOrderNumber()
    {
        return orderNum;
    }

    /**
     * Method returns the customer name
     *
     * @return The customer name
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
     * Method returns all of the drinks from the order
     *
     * @return The list of drinks ordered
     */
    @Override
    public String toString()
    {
        String str = "";
        str += "ord: " + orderNum + ",  ";
        str += "emp: " + bartenderName + ",  " + "cust: " + customerName + ",  ";
        str += "date: " + date + ", " + "drinks: ";
        
        for(DrinkAndQuantity d : drinksAndQuantities){
            str += d.toString();
        }
        return str;
    }
}

