package barManagement;

import java.util.ArrayList;

public class MixedDrink extends Drink implements MixedDrinkRecipe
{
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
                setType("Mixed Drink");
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
    //public String getGarnish(){
    //    return garnish.getGarnish();
    //}
    
    //getGarnish
    @Override
    public String getGarnish(){
        return garnish.garnish();
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((garnish == null) ? 0 : garnish.hashCode());
		result = prime * result + ((liquor == null) ? 0 : liquor.hashCode());
		result = prime * result + ((mixer == null) ? 0 : mixer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MixedDrink other = (MixedDrink) obj;
		if (garnish == null) {
			if (other.garnish != null)
				return false;
		} else if (!garnish.equals(other.garnish))
			return false;
		if (liquor == null) {
			if (other.liquor != null)
				return false;
		} else if (!liquor.equals(other.liquor))
			return false;
		if (mixer == null) {
			if (other.mixer != null)
				return false;
		} else if (!mixer.equals(other.mixer))
			return false;
		return true;
	}

    

}
