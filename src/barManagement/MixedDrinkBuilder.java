package barManagement;

public class MixedDrinkBuilder implements DrinkBuilder
{
    private MixedDrink mixedDrink;

    public MixedDrinkBuilder(String name){
        this.mixedDrink = new MixedDrink(name);   
    }

    public void mixIngredients(String name){

        IceStrategy ice;
        GlassStrategy glass;
        GarnishStrategy garnish;
        switch (name)
        {

            case "Gin Martini":
            buildMenuName("Gin Martini");
            buildLiquor("gin", 2.0);
            buildLiquor("vermouth", 1.0);
            buildGarnish(garnish = new Olive());
            buildIce(ice = new Strained());
            buildGlass(glass = new Martini());
            buildPrice(10.99);
            buildSize(3.0);

            case "Frozen Margarita":
            buildMenuName("Frozen Margarita");
            buildLiquor("tequila", 2.0);
            buildLiquor("cordial", 1.0);
            buildMixer("lime", 1.0);
            buildGarnish(garnish = new Lime());
            buildIce(ice = new Frozen());
            buildGlass(glass = new Margarita());
            buildPrice(9.99);
            buildSize(4.0);

            case "Baybreeze":
            buildMenuName("Baybreeze");
            buildLiquor("rum", 2.0);
            buildMixer("pineapple", 2.0);
            buildMixer("cranberry", 2.0);
            buildGarnish(garnish = new Umbrella());
            buildIce(ice = new Cube());
            buildGlass(glass = new Pint());
            buildPrice(7.99);
            buildSize(6.0);            

            case "Cosmopolitan":
            buildMenuName("Cosmopolitan");
            buildLiquor("vodka", 1.5);
            buildLiquor("cordial", 1.0);
            buildMixer("cranberry", .5);
            buildMixer("lime", .5);
            buildGarnish(garnish = new Lemon());
            buildIce(ice = new Strained());
            buildGlass(glass = new Martini());
            buildPrice(10.99);
            buildSize(3.5);            

            case "Bourbon Manhattan":
            buildMenuName("Bourbon Manhattan");
            buildLiquor("bourbon", 2.0);
            buildLiquor("vermouth", 1.0);
            buildGarnish(garnish = new Cherry());
            buildIce(ice = new Strained());
            buildGlass(glass = new Martini());
            buildPrice(11.99);
            buildSize(3.0);

            case "Rum and Coke":
            buildMenuName("Rum and Coke");
            buildLiquor("rum", 1.5);
            buildMixer("coke", 3.0);
            buildGarnish(garnish = new Lime());
            buildIce(ice = new Cube());
            buildGlass(glass = new Pint());
            buildPrice(6.99);
            buildSize(4.5);            
        }
    }

    public void buildMenuName(String newMenuName){
        mixedDrink.setMenuListing(newMenuName);
    }
    //buildLiquor
    public void buildLiquor(String liquor, double amount){
        Liquor liquorComponent = new Liquor(liquor, amount);
        mixedDrink.setLiquor(liquorComponent);
    }
    //buildMixer
    public void buildMixer(String mixer, double amount){
        NonAlcoholic mixerComponent = new NonAlcoholic(mixer, amount);
        mixedDrink.setMixer(mixerComponent);
    }
    //buildGarnish
    public void buildGarnish(GarnishStrategy garnish){
        mixedDrink.setGarnish(garnish);
    }
    //buildIce
    public void buildIce(IceStrategy ice){
        mixedDrink.setIce(ice);
    }
    //buildGlass
    public void buildGlass(GlassStrategy glass){
        mixedDrink.setGlass(glass);
    }
    //buildPrice
    public void buildPrice(double drinkPrice){
        mixedDrink.setPrice(drinkPrice);
    }

    //buildSize
    public void buildSize(double drinkSize){
        mixedDrink.setSize(drinkSize);
    }

    //getDrink
    public MixedDrink getDrink(){
        return mixedDrink;
    }
}

