package barManagement;

import java.util.TreeMap;
import java.util.Map;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
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

    public Order getOrder(Integer orderNumber){
        boolean found = false;
        Iterator it = orderStorage.entrySet().iterator();
        Order value = null;
        while(it.hasNext() && !found){
            Map.Entry keyValuePair = (Map.Entry)it.next();
            Integer key = (Integer)(keyValuePair.getKey());
            value = (Order)(keyValuePair.getValue());
        }
        return value;
    }

    public void updatePayment(Integer order, boolean newPayment, String payType){
        orderStorage.get(order).setPayment(newPayment);
        orderStorage.get(order).setPaymentType(payType);
    }

    public int getSize(){
        return orderStorage.size();   
    }

    public String[] getOpenOrders(){
        // ArrayList<Order> orderAL = orderStorage.values().stream()
        // .filter(val ->!val.getPayment())
        // .map(ord ->(Order)(ord))
        // .collect(Collectors.toCollection(ArrayList::new));

        // ArrayList<String> orderAL = orderStorage.values().stream()
        // .filter(val ->!val.getPayment())
        // .map(ord -> ord.getCustomerName())
        // .collect(Collectors.toCollection(ArrayList::new));

        // String[] openOrders = orderAL.toArray();
        int count = 0;
        for(Map.Entry<Integer,Order> entry: orderStorage.entrySet()) {
            Integer key = entry.getKey();
            Order value = entry.getValue();
            boolean valtest = value.getPayment();
            if(!value.getPayment()){
                count++;
            }
        }
        String[] openOrders = new String[count];
        int arrCount = 0;
        for(Map.Entry<Integer,Order> entry: orderStorage.entrySet()) {
            Integer key = entry.getKey();
            Order value = entry.getValue();
            if(!value.getPayment()){
                //openOrders[arrCount] = "#" + key +" "+ value.getCustomerName();
                openOrders[arrCount] = "#" + key;
                arrCount++;
            }
        }
        return openOrders;
    }

    public void printAll(){
        orderStorage.forEach((key, value) -> System.out.println(value));
    }
}