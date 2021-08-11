package barManagement;

public class Liquor extends Drink
{
    
    
    /**
     * Constructor for objects of class Liquor
     */
    public Liquor(String name,double price, boolean shot)
    {
        super(name);
        glassType = new Shot();
        setType("Liquor Shot");
        setName(name);
        setSize(1.5);
        setPrice(price);

    }

    public Liquor(String name, double portionSize)
    {
        super(name);
        setType("Mixed Drink");
        setName(name);
        setSize(portionSize);
    }

}