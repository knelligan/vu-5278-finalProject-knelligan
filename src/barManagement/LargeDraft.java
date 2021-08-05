package barManagement;

public class LargeDraft extends DraftBeer
{
    String productName;
    /**
     * Constructor for objects of class Wine
     */
    public LargeDraft(String name)
    {
        super(name);
        glassType = new Pilsner();
        setSize(20.0);
        setName(name);
        setType("Large Draft Beer");

    }
        /**
     * Constructor for objects of class DraftBeer
     */
    public LargeDraft(String newName, String newMenuName, String prodName, double newPrice)
    {
        super(newName);
        setType("Large Draft Beer");
        setPrice(newPrice);
        setSize(20.0);
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