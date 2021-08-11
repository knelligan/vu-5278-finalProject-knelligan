package barManagement;

import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.File;
import java.io.IOException;  
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class InventoryDB {
    TreeMap<String, Double> quantity;
    TreeMap<String, Double> price;
    TreeMap<String, Double> cost;
    TreeMap<String, Boolean> outOfStock;

    //Constants: Working under assumption inventory.csv has this column format
    private static final int ITEM = 0;
    private static final int TYPE = 1;
    private static final int STOCK_SIZE = 2;
    private static final int STOCK_COST = 3;
    private static final int COST_PER_OZ = 4;
    private static final int ON_HAND = 5;

    //Separate file for prices of menu items
    private static final int MENU_PRICE = 1;
    /**
     * Constructor for objects of class Inventory
     */
    public InventoryDB()
    {
        quantity = new TreeMap();
        price = new TreeMap();
        cost = new TreeMap();
        outOfStock = new TreeMap();
        readInventory();
        readPriceList();
    }
    

    public TreeMap<String, Double> getQuantities(){
        return quantity;   
    }

    public TreeMap<String, Double> getCosts(){
        return cost;   
    }

    public TreeMap<String, Double> getPrices(){
        return price;   
    }

    public TreeMap<String, Boolean> getOutOfStock(){
        return outOfStock;   
    }
    
        public double getDrinkPrice(String drinkName){
            double menuPrice = price.get(drinkName);
            return menuPrice;   
    }

    public void readInventory(){
        String line = "";  
        String splitBy = ",";  

        try   
        {  

            BufferedReader br = new BufferedReader(new FileReader("src/barManagement/inventory.csv"));  
            while ((line = br.readLine()) != null)   
            {  
                String[] currentRow = line.split(splitBy); 

                if(!currentRow[ITEM].equals("") && !currentRow[ITEM].contains("Item")){
                    if(!currentRow[ON_HAND].equals("")){
                        //creates quantity node for selected item
                        double quantityConvert = Double.parseDouble(currentRow[ON_HAND]);
                        insertQuantity(currentRow[ITEM], quantityConvert);
                    }
                    if(!currentRow[ON_HAND].equals("")){
                        //creates out of stock node for item
                        double quantityConvert = Double.parseDouble(currentRow[ON_HAND]);
                        if(quantityConvert > 0){
                            insertOutOfStock(currentRow[ITEM],true);
                        }else{
                            insertOutOfStock(currentRow[ITEM],false);
                        }
                    }
                    if(!currentRow[COST_PER_OZ].equals("")){
                        //creates cost node for selected item
                        double costConvert = Double.parseDouble(currentRow[COST_PER_OZ]);
                        insertCost(currentRow[ITEM], costConvert);
                    }
                }

            }  
            br.close();
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
    }  

    public void readPriceList(){
        String line = "";  
        String splitBy = ",";  

        try   
        {  

            BufferedReader br = new BufferedReader(new FileReader("src/barManagement/priceList.csv"));  
            while ((line = br.readLine()) != null)   
            {  
                String[] currentRow = line.split(splitBy); 

                if(!currentRow[ITEM].equals("") && !currentRow[ITEM].contains("Item")){
                    if(!currentRow[MENU_PRICE].equals("")){
                        //creates price node for selected item
                        double priceConvert = Double.parseDouble(currentRow[MENU_PRICE]);
                        insertPrice(currentRow[ITEM], priceConvert);
                    }
                }

            }  
            br.close();
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
    }  

    public void updateCSV(){
        String line = "";  
        String splitBy = ",";  

        try   
        {  

            BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));  
            while ((line = br.readLine()) != null)   
            {  
                String[] currentRow = line.split(splitBy);    
                if(!currentRow[ITEM].equals("") && !currentRow[ITEM].equals("Item")){
                    if(!currentRow[ON_HAND].equals("")){
                        //creates quantity node for selected item
                        double quantityConvert = Double.parseDouble(currentRow[ON_HAND]);
                        insertQuantity(currentRow[ITEM], quantityConvert);
                    }else if(!currentRow[ON_HAND].equals("")){
                        //creates out of stock node for item
                        double quantityConvert = Double.parseDouble(currentRow[ON_HAND]);
                        if(quantityConvert >= 0.5){
                            insertOutOfStock(currentRow[ITEM],true);
                        }else{
                            insertOutOfStock(currentRow[ITEM],false);
                        }
                    }
                    if(!currentRow[COST_PER_OZ].equals("")){
                        //creates cost node for selected item
                        double costConvert = Double.parseDouble(currentRow[COST_PER_OZ]);
                        insertCost(currentRow[ITEM], costConvert);
                    }
                    if(!currentRow[MENU_PRICE].equals("")){
                        //creates price node for selected item
                        double priceConvert = Double.parseDouble(currentRow[MENU_PRICE]);
                        insertPrice(currentRow[ITEM], priceConvert);
                    }
                }

            }  
            br.close();
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
    }  

    public void insertQuantity(String name, Double amount){
        quantity.put(name, amount);
    }

    public void insertCost(String name, Double itemCost){
        cost.put(name, itemCost);
    }

    public void insertPrice(String name, Double itemPrice){
        price.put(name, itemPrice);
    }

    public void insertOutOfStock(String name, Boolean inStock){
        outOfStock.put(name, inStock);
    }

    public void updateOutOfStock(String name, Boolean inStock){
        outOfStock.replace(name, inStock);
    }

    public double getCurrentQuantity(String name){

        //code to test
        double currentQuantity  = quantity.get(name);
        System.out.println(name + " on hand: " + currentQuantity + "oz.");
        return currentQuantity;
    }

    public boolean checkStock(String name, double amount){
        boolean inStock = false;
        double currentQuantity  = quantity.get(name);

        if(currentQuantity >= amount){
            inStock = true;
        }
        return inStock;
    }

    public boolean productInStock(String name){

        boolean inStock = true;
        inStock = outOfStock.get(name);
        return inStock;
    }

    /**
     * This method only takes away from the current quantity. To add more use 
     * addStock method.
     *
     * @param name A parameter
     * @param amount A parameter
     */
    public boolean updateQuantity(String name, double amount){
        //print test before
        System.out.println("before updated");
        getCurrentQuantity(name);

        boolean enough = false;
        double oldValue = quantity.get(name);

        double newValue = oldValue - amount;

        if(newValue >= 0.0){
            quantity.replace(name, newValue);
            enough = true;
            if(newValue <= 0.49){
                updateOutOfStock(name, false);
            }
        }else{
            updateOutOfStock(name, false);
        }

        System.out.println("after updated");
        getCurrentQuantity(name);

        //overwrite inventory csv file
        if(enough){
            writeInventoryFile();   
        }

        return enough;
    }

    public void writeInventoryFile() {
        PrintWriter pw = null;
        try {
        	//this is set to a different filename/directory, but should simply overwrite inventory.csv
            pw = new PrintWriter(new File("inventory2.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();

        //create header row
        String columnNamesList = "Item,Type,OzPerStockUnit,StockCost,CostPerOz,TotalOzOnHand";
        builder.append(columnNamesList +"\n");

        String line = "";  
        String splitBy = ",";  

        try   
        {  

            BufferedReader br = new BufferedReader(new FileReader("src/barManagement/inventory.csv"));  
            while ((line = br.readLine()) != null)   
            {  
                String[] currentRow = line.split(splitBy);    
                if(!currentRow[ITEM].equals("") && !currentRow[ITEM].contains("Item")){
                    //add all the fields that do not change
                    String newRow = currentRow[ITEM] + ", " + currentRow[TYPE] + ", ";
                    newRow += currentRow[STOCK_SIZE] + ", " + currentRow[STOCK_COST] + ", ";
                    newRow += currentRow[COST_PER_OZ] + ", ";

                    //get changes to stock on hand
                    String itemName = currentRow[ITEM];
                    newRow += compareStock(itemName);

                    //append to string builder
                    builder.append(newRow +"\n");

                }

            }  
            br.close();
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  

        pw.write(builder.toString());
        pw.close();
        
    }

    public String compareStock(String itemName){
        double amount  = quantity.get(itemName);

        return "" + amount;
    }
}


