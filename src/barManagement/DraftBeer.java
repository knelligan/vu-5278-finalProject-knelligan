package barManagement;

/**
 * Write a description of class DraftBeer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DraftBeer extends Beer {
	String productName;

	/**
	 * Constructor for objects of class DraftBeer
	 */
	public DraftBeer(String name) {
		super(name);
		setType("Draft Beer");
	}
@Override
	public String getProductName() {
		return productName;
	}

}
