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
        str += getDrink().toString();
        //str = str.substring(0, str.length() - 1);
        return str;
        

    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drink == null) ? 0 : drink.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrinkAndQuantity other = (DrinkAndQuantity) obj;
		if (drink == null) {
			if (other.drink != null)
				return false;
		} else if (!drink.equals(other.drink))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
}