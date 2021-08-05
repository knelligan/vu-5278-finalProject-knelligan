package barManagement;

import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;
/**
 * Write a description of class OrderDB here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OrderDB
{
    // instance variables - replace the example below with your own

    TreeMap<Integer, Order> orderStorage;
    int size;
    /**
     * Constructor for objects of class OrderDB
     */
    public OrderDB()
    {
        orderStorage = new TreeMap();
        size = 0;
    }

    public void insert(Integer orderNum, Order order){
        orderStorage.put(orderNum, order);
    }

    public void delete(Integer orderNumber){
        while(orderStorage.containsKey(orderNumber)){
            orderStorage.remove(orderNumber);   
        }
    }

    public boolean contains(Integer orderNumber){
        boolean found = false;
        Iterator it = orderStorage.entrySet().iterator();
        while(it.hasNext() && !found){
            Map.Entry keyValuePair = (Map.Entry)it.next();
            Integer key = (Integer)(keyValuePair.getKey());
            if(key == orderNumber){
                found = true;   
            }
        }
        return found;
    }

    public int getSize(){
        return orderStorage.size();   
    }
    
    public void printAll(){
        orderStorage.forEach((key, value) -> System.out.println(value));
    }
}
