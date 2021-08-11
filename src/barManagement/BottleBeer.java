package barManagement;

public class BottleBeer extends Beer
{

    /**
     * Constructor for objects of class Wine
     */
    public BottleBeer(String name)
    {
        super(name);
        setType("Bottle Beer");
        setSize(12);
        setName(name);
    }

    /**
     * Constructor for objects of class DraftBeer
     */
    public BottleBeer(String newName, String prodName, double newPrice)
    {
        super(newName);
        setType("Bottle Beer");
        setPrice(newPrice);
        setSize(12);
        productName = prodName;

    }
}
