package barManagement;

/**
 * Write a description of class Wine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wine extends Drink {

	/**
	 * Constructor for objects of class Wine
	 */
	public Wine(String name) {
		super(name);
		setType("Wine");
		setSize(5.0);

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