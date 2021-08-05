package barManagement;

public class Liquor extends Drink
{
    
    /**
     * Constructor for objects of class Liquor
     */
    public Liquor(String name, String newMenuName, double price)
    {
        super(name);
        glassType = new Shot();
        setType("Liquor Shot");
        setName(name);
        setSize(1.5);
        setMenuListing(newMenuName);
    }
    
    public Liquor(String name, double portionSize)
    {
        super(name);
        setType("Mixed Drink");
        setName(name);
    }
    
    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 
    }
}

