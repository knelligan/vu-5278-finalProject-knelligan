package barManagement;

public class LargeDraft extends DraftBeer
{
	   String productName;
	    /**
	     * Constructor for objects of class Wine
	     */
	    public LargeDraft(String name)
	    {
	        super(name);
	        glassType = new Pilsner();
	        setSize(20.0);
	        setName(name);
	        setType("Large Draft Beer");

	    }
	        /**
	     * Constructor for objects of class DraftBeer
	     */
	    public LargeDraft(String newName, String prodName, double newPrice)
	    {
	        super(newName);
	        glassType = new Pilsner();
	        setType("Large Draft Beer");
	        setPrice(newPrice);
	        setSize(20.0);
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
			int result = 1;
			result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
			LargeDraft other = (LargeDraft) obj;
			if (productName == null) {
				if (other.productName != null)
					return false;
			} else if (!productName.equals(other.productName))
				return false;
			return true;
		}
	    

	}