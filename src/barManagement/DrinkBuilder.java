package barManagement;

public interface DrinkBuilder
{
public void mixIngredients(String name);

public void buildMenuName(String name);
    //buildLiquor
public void buildLiquor(String liquor, double amount);
//buildMixer
public void buildMixer(String mixer, double amount);
//buildGarnish
public void buildGarnish(GarnishStrategy garnish);
//buildIce
public void buildIce(IceStrategy ice);
//buildGlass
public void buildGlass(GlassStrategy glass);
//buildPrice
public void buildPrice(double drinkPrice);
//getDrink
public void buildSize(double size);
//getDrink
public MixedDrink getDrink();
}