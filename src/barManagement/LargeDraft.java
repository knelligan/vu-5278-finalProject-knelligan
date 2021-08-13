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
	    

	}