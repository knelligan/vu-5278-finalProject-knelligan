package barManagement;

public class SmallDraft extends DraftBeer {
	String productName;

	/**
	 * Constructor for objects of class small draft beer
	 */
	public SmallDraft(String name) {
		super(name);
		glassType = new Mug();
		setSize(12.0);
		setName(name);
		setType("Small Draft Beer");
	}

	/**
	 * Constructor for objects of class DraftBeer
	 */
	public SmallDraft(String newName, String prodName, double newPrice) {
		super(newName);
		setType("Small Draft Beer");
		glassType = new Mug();
		setPrice(newPrice);
		setSize(12.0);
		productName = prodName;

	}

	@Override
	public String getProductName() {
		return productName;
	}

	@Override
	public void setProductName(String newProduct) {
		productName = newProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmallDraft other = (SmallDraft) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

}
