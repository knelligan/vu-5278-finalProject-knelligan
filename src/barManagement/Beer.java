package barManagement;

/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Beer extends Drink
{
    String productName;
    /**
     * Constructor for objects of class Beer
     */
    public Beer(String name)
    {
        super(name);
        setType("Beer");
    }
}