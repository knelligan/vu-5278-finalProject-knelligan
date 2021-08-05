package barManagement;

public class NonAlcoholic extends Drink
{

    /**
     * Constructor for objects of class NonAlcoholic
     */
    public NonAlcoholic(String name)
    {
        super(name);
        setType("NonAlcoholic");

    }

    /**
     * Constructor for objects of class NonAlcoholic
     */
    public NonAlcoholic(String name, String newMenuName,double newPrice)
    {
        super(name);
        setType("NonAlcoholic");
        setSize(6.0);
        glassType = new Pint();
        iceType = new Cube();
        setPrice(newPrice);
        setMenuListing(newMenuName);
    }

    /**
     * Overloaded constructor for when the non-alcoholic beverage is a component
     * of a mixed drink.
     */
    public NonAlcoholic(String name, double portionSize)
    {
        super(name);
        setType("Non-Alcoholic");
        setSize(portionSize); 
    }

    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 
    }
}
