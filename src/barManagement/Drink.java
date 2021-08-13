package barManagement;

/**
 * A class to encapsulate information about a drink.
 *
 * @author Kate Nelligan
 * @version Summer 2021
 */
public abstract class Drink{
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((garnishType == null) ? 0 : garnishType.hashCode());
		result = prime * result + ((glassType == null) ? 0 : glassType.hashCode());
		result = prime * result + ((iceType == null) ? 0 : iceType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Drink other = (Drink) obj;
		if (garnishType == null) {
			if (other.garnishType != null)
				return false;
		} else if (!garnishType.equals(other.garnishType))
			return false;
		if (glassType == null) {
			if (other.glassType != null)
				return false;
		} else if (!glassType.equals(other.glassType))
			return false;
		if (iceType == null) {
			if (other.iceType != null)
				return false;
		} else if (!iceType.equals(other.iceType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	// drink info
    private String name;
    private String type;
    private double size;
    private double price;
    private String productName;

    /** Ice type for drink */
    protected IceStrategy iceType;

    /** Glass type for drink*/
    protected GlassStrategy glassType;
    
    /** Garnish type for drink*/
    protected GarnishStrategy garnishType;

    /**
     * Creates a drink object
     *
     * @param size the size of the drink
     * @param type the type of drink to create
     */
    public Drink(String name){
        this.name = name;
    }

    public String getIce(){
        return iceType.ice();   
    }

    public String getGlass(){
        return glassType.glass();   
    }
    
    public String getGarnish(){
        return garnishType.garnish();   
    }

    public void setIce(IceStrategy newIceType){
        iceType = newIceType;
    }

    public void setGlass(GlassStrategy newGlassType){
        glassType = newGlassType;
    }

    public String getName(){
        return name;   
    }

    public void setName(String newName){
        name = newName;   
    }

    public double getPrice(){
        return price;   
    }

    public void setPrice(double newPrice){
        price = newPrice;   
    }

    public double getSize(){
        return size;   
    }

    public void setSize(double newSize){
        size = newSize;   
    }

    public String getType(){
        return type;   
    }

    public void setType(String newType){
        type = newType;   
    }
    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String newProduct) {
		productName = newProduct;
	}
    
    @Override
    public String toString(){
        //return getMenuListing();  
        String str = "";
        str += getName();
        return str;
    }


}

