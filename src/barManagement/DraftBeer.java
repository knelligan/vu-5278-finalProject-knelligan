package barManagement;


/**
 * Write a description of class DraftBeer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DraftBeer extends Beer
{

    /**
     * Constructor for objects of class DraftBeer
     */
    public DraftBeer(String name)
    {
        super(name);
        setType("Draft Beer");
    }

    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 
    }
}