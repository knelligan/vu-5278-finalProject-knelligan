package barManagement;


/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WhiteWine extends Wine
{

    /**
     * Constructor for objects of class Wine
     */
    public WhiteWine(String name)
    {
        super(name);
        glassType = new WhiteGlass();
        setPrice(8.00);
        setName(name);
        setType("White Wine");

    }

}