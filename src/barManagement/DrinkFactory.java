package barManagement;


/**
 * Write a description of class DrinkFactory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DrinkFactory
{

    public Drink makeDrink(String name){
        Drink newDrink = null;


            if(name.equals("Red wine")){
                newDrink = new RedWine(name);
            }else if(name.equals("White wine")){
                newDrink = new WhiteWine(name);
            }else if(name.equals("Coke")){
                newDrink = new NonAlcoholic(name, "Coke", 2.0);
            }else if(name.equals("Sprite")){
                newDrink = new NonAlcoholic(name, "Sprite", 2.0);
            }else if(name.equals("Ginger ale")){
                newDrink = new NonAlcoholic(name, "Ginger Ale", 2.0);
            }else if(name.equals("Orange Juice")){
                newDrink = new NonAlcoholic(name, "Orange Juice", 3.0);
            }else if(name.equals("Cranberry Juice")){
                newDrink = new NonAlcoholic(name, "Cranberry Juice", 3.0);
            }else if(name.equals("Pineapple Juice")){
                newDrink = new NonAlcoholic(name, "Pineapple Juice", 3.0);
            }else if(name.equals("Lime Juice")){
                newDrink = new NonAlcoholic(name, "Lime Juice", 3.0);
            }else if(name.equals("Bud Light")){
                newDrink = new BottleBeer(name, "Bud Light Bottle", "Bud Light", 4.00);
            }else if(name.equals("Corona")){
                newDrink = new BottleBeer(name, "Corona Bottle", "Corona", 5.0);
            }else if(name.equals("Heineken")){
                newDrink = new BottleBeer(name, "Heineken Bottle", "Heineken", 6.0);
            }else if(name.equals("Small Guiness")){
                newDrink = new SmallDraft(name, "Small Guiness Draft", "Guiness", 7.0);
            }else if(name.equals("Small Coors Light")){
                newDrink = new SmallDraft(name, "Small Coors Light Draft", "Coors Light", 5.0);
            }else if(name.equals("Small Sam Adams")){
                newDrink = new SmallDraft(name, "Small Sam Adams Draft", "Samuel Adams", 6.0);
            }else if(name.equals("Large Guiness")){
                newDrink = new LargeDraft(name, "Large Guiness Draft", "Guiness", 9.0);
            }else if(name.equals("Large Coors Light")){
                newDrink = new LargeDraft(name, "Large Coors Light Draft", "Coors Light", 7.0);
            }else if(name.equals("Large Sam Adams")){
                newDrink = new LargeDraft(name, "Large Sam Adams Draft", "Samuel Adams", 8.0);
            }else if(name.equals("Vodka Shot")){
                newDrink = new Liquor(name, "Vodka Shot", 2.50);
            }else if(name.equals("Whiskey Shot")){
                newDrink = new Liquor(name, "Whiskey Shot", 4.00);
            }else if(name.equals("Tequila Shot")){
                newDrink = new Liquor(name, "Tequila Shot", 3.00); 
            }else if(name.equals("Gin Shot")){
                newDrink = new Liquor(name, "Gin Shot", 2.75);
            }else if(name.equals("Bourbon Shot")){
                newDrink = new Liquor(name, "Bourbon Shot", 5.00);
            }else if(name.equals("Rum Shot")){
                newDrink = new Liquor(name, "Rum Shot", 3.25);     
            }else if(name.equals("Cordial Shot")){
                newDrink = new Liquor(name, "Cordial Shot", 2.75);    
            }else if(name.equals("Vermouth Shot")){
                newDrink = new Liquor(name, "Vermouth Shot", 2.25);   
            }else{
                //call mixologist
                MixedDrinkBuilder mdb = new MixedDrinkBuilder(name);
                //call mixologist using
                Mixologist mixologist = new Mixologist(mdb);
                //make the drink
                mixologist.makeDrink(name);

                newDrink = mixologist.getDrink();
            }



        return newDrink;
    }
}

