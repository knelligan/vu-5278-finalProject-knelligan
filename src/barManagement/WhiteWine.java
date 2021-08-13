package barManagement;

/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WhiteWine extends Wine {

	/**
	 * Constructor for objects of class Wine
	 */
	public WhiteWine(String name) {
		super(name);
		glassType = new WhiteGlass();
		setPrice(8.00);
		setName(name);
		setType("White Wine");

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