package barManagement;


/**
 * Write a description of class DrinkAndQuantity here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DrinkAndQuantity{

    private Integer quantity;

    private Drink drink;

    public void setDrink(Drink newDrink){
        drink = newDrink;
    }

    public void setQuantity(Integer newQuantity){
        quantity = newQuantity;
    }

    public Drink getDrink(){
        return drink;
    }

    public Integer getQuantity(){
        return quantity;
    }

    @Override
    public String toString(){
        // StringBuilder str = new StringBuilder();
        // str.append("" + quantity +", ");
        // str.append(getDrink().toString());
        // return str.toString();
        String str = "";
        str += quantity + " ";
        str += getDrink().toString() + " ";
        //str += "test";
        return str;
        

    }
}
