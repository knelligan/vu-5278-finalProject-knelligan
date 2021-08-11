package barManagement;

public class Mixologist
{
    private MixedDrinkBuilder mdb;

    /**
     * Constructor for objects of class Mixologist
     */
    public Mixologist(MixedDrinkBuilder mdb)
    {
        this.mdb = mdb;
    }

    public void makeDrink(String name){
        this.mdb.mixIngredients(name);
    }
    public MixedDrink getDrink(){
        return this.mdb.getDrink();
    }
}