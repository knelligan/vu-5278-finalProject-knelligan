package barManagement;


/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wine extends Drink
{

    /**
     * Constructor for objects of class Wine
     */
    public Wine(String name)
    {
        super(name);
        setType("Wine");
        setSize(5.0);

    }
}