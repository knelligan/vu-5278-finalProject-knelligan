package barManagement;
import java.util.ArrayList;
public class MixedDrink extends Drink implements MixedDrinkRecipe
{
    //each drink should have max of 1.5 oz liquor
    //rocks drink should be 2.0 oz liquor
    //17 shots per bottle

    //Recipe recipe;
    //Garnish garnish;

    //Drinks may have more than one liquor and mixer
    private ArrayList<Liquor> liquor;
    private ArrayList<NonAlcoholic> mixer;
    private GarnishStrategy garnish;
    /**
     * Constructor for objects of class Wine
     */
    public MixedDrink(String name)
    {
        super(name);
        glassType = new Pint();
        iceType = new Cube();
        setName(name);
        mixer = new ArrayList();
        liquor = new ArrayList();
    }
    //buildLiquor
    public ArrayList<Liquor> getLiquor(){
        return liquor;
    }
    //buildMixer
    public ArrayList<NonAlcoholic> getMixer(){
        return mixer;
    }
    //buildGarnish
    public GarnishStrategy getGarnish(){
        return garnish;
    }

    //don't need other methods they are in main interface

    //buildPrice
    public double getPrice(){
        return getPrice();
    }

    //buildLiquor
    public void setLiquor(Liquor newLiquor){
        liquor.add(newLiquor);
    }
    //buildMixer
    public void setMixer(NonAlcoholic newMixer){
        mixer.add(newMixer);
    }
    //buildGarnish
    public void setGarnish(GarnishStrategy newGarnish){
        garnish = newGarnish;
    }
    //buildIce
    public void setIce(IceStrategy newIce){
        iceType = newIce;
    }
    //buildGlass
    public void setGlass(GlassStrategy newGlass){
        glassType = newGlass;
    }

    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 
    }


}