package barManagement;

/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RedWine extends Wine {

	/**
	 * Constructor for objects of class Wine
	 */
	public RedWine(String name) {
		super(name);
		glassType = new RedGlass();
		setPrice(12.00);
		setName(name);
		setType("Red Wine");

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
