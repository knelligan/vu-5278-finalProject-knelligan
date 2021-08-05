package barManagement;


/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RedWine extends Wine
{

    /**
     * Constructor for objects of class Wine
     */
    public RedWine(String name)
    {
        super(name);
        glassType = new RedGlass();
        setPrice(12.00);
        setName(name);
        setType("Red Wine");
        setMenuListing("Red Wine");
    }

    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 
    }
}