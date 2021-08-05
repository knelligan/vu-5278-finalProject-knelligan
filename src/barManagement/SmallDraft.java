package barManagement;

public class SmallDraft extends DraftBeer
{
    String productName;
    /**
     * Constructor for objects of class small draft beer
     */
    public SmallDraft(String name)
    {
        super(name);
        glassType = new Mug();
        setSize(12.0);
        setName(name);
        setType("Small Draft Beer");
    }

    /**
     * Constructor for objects of class DraftBeer
     */
    public SmallDraft(String newName, String newMenuName, String prodName, double newPrice)
    {
        super(newName);
        setType("Small Draft Beer");
        setPrice(newPrice);
        setSize(12.0);
        productName = prodName;
        setMenuListing(newMenuName);
    }

    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 
    }
}