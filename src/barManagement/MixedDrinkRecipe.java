package barManagement;

public interface MixedDrinkRecipe
{
	//buildLiquor
	public void setLiquor(Liquor liquor);
	//buildMixer
	public void setMixer(NonAlcoholic mixer);
	//buildGarnish
	public void setGarnish(GarnishStrategy garnish);
	//buildIce
	public void setIce(IceStrategy ice);
	//buildGlass
	public void setGlass(GlassStrategy glass);
	}
