package barManagement;


/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main 
{
    public static final OrderDB orderDB = new OrderDB();
    public static final InventoryDB inventoryDB = new InventoryDB();

    public static void main(String[] args){


        OrderPanel orderPanel = new OrderPanel();
        orderPanel.main(orderDB, inventoryDB);

    }

    public OrderDB getOrderDatabase(){
        return orderDB;   
    }


}