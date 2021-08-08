package barManagement;

public interface DrinkBuilder
{
	public void mixIngredients(String name);

	public void buildLiquor(String liquor, double amount);

	public void buildMixer(String mixer, double amount);

	public void buildGarnish(GarnishStrategy garnish);

	public void buildIce(IceStrategy ice);

	public void buildGlass(GlassStrategy glass);

	public void buildPrice(double drinkPrice);

	public void buildSize(double size);

	public MixedDrink getDrink();
	}
