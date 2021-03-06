package barManagement;

public class NonAlcoholic extends Drink {

	/**
	 * Constructor for objects of class NonAlcoholic
	 */
	public NonAlcoholic(String name) {
		super(name);
		setType("NonAlcoholic");

	}

	/**
	 * Constructor for objects of class NonAlcoholic
	 */
	public NonAlcoholic(String name, double newSize, double newPrice) {
		super(name);
		setType("NonAlcoholic");
		setSize(newSize);
		glassType = new Pint();
		iceType = new Cube();
		setPrice(newPrice);

	}

	/**
	 * Overloaded constructor for when the non-alcoholic beverage is a component of
	 * a mixed drink.
	 */
	public NonAlcoholic(String name, double portionSize) {
		super(name);
		setType("Non-Alcoholic");
		setSize(portionSize);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
