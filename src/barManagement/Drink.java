package barManagement;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * A class to encapsulate information about a drink.
 *
 * @author Kate Nelligan
 * @version Summer 2021
 */
public abstract class Drink{
    // drink info
    private String name;
    private String menuName;
    private String type;
    private double size;
    private double price;

    /** Ice type for drink */
    protected IceStrategy iceType;

    /** Glass type for drink*/
    protected GlassStrategy glassType;

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

    public String getMenuListing(){
        return menuName;   
    }

    public void setMenuListing(String newMenuName){
        menuName = menuName;   
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
    
    @Override
    public String toString(){
        //return getMenuListing();  
        String str = "";
        str += getName();
        return str;
    }

    //this abstract method should maybe get price and/or update inventory
    /**
     * Updates the inventory
     */
    public void updateInventory(String name, double size){
        //update 

    }
}

