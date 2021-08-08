package barManagement;

/**
 * Write a description of class MixedDrinkBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
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

        if(name.equals("Gin Martini")){

            buildLiquor("Gin Shot", 2.0);
            buildLiquor("Vermouth Shot", 1.0);
            buildGarnish(garnish = new Olive());
            buildIce(ice = new Strained());
            buildGlass(glass = new Martini());
            buildPrice(10.99);
            buildSize(3.0);
        }else if(name.equals("Frozen Margarita")){
            buildLiquor("Tequila Shot", 2.0);
            buildLiquor("Cordial Shot", 1.0);
            buildMixer("Lime Juice", 1.0);
            buildGarnish(garnish = new Lime());
            buildIce(ice = new Frozen());
            buildGlass(glass = new Margarita());
            buildPrice(9.99);
            buildSize(4.0);
        }else if(name.equals("Baybreeze")){
            buildLiquor("Rum Shot", 2.0);
            buildMixer("Pineapple Juice", 2.0);
            buildMixer("Cranberry Juice", 2.0);
            buildGarnish(garnish = new Umbrella());
            buildIce(ice = new Cube());
            buildGlass(glass = new Pint());
            buildPrice(7.99);
            buildSize(6.0);            
        }else if(name.equals("Cosmopolitan")){
            buildLiquor("Vodka Shot", 1.5);
            buildLiquor("Cordial Shot", 1.0);
            buildMixer("Cranberry Juice", .5);
            buildMixer("Lime", .5);
            buildGarnish(garnish = new Lemon());
            buildIce(ice = new Strained());
            buildGlass(glass = new Martini());
            buildPrice(10.99);
            buildSize(3.5);            
        }else if(name.equals("Bourbon Manhattan")){
            buildLiquor("Bourbon Shot", 2.0);
            buildLiquor("Vermouth Shot", 1.0);
            buildGarnish(garnish = new Cherry());
            buildIce(ice = new Strained());
            buildGlass(glass = new Martini());
            buildPrice(11.99);
            buildSize(3.0);
        }else if(name.equals("Rum and Coke")){
            buildLiquor("Rum Shot", 1.5);
            buildMixer("Coke", 3.0);
            buildGarnish(garnish = new Lime());
            buildIce(ice = new Cube());
            buildGlass(glass = new Pint());
            buildPrice(6.99);
            buildSize(4.5);            
        }

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
