package barManagement;


/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main 
{
    public static final OrderDB orderDB = new OrderDB();
    //adapted from tutorial: https://www.zentut.com/java-swing/simple-login-dialog/ 
    public static boolean enterDashboard = false;


    public static void main(String[] args){

        Dashboard dash = new Dashboard();
        dash.main(orderDB);

    }

    public OrderDB getOrderDatabase(){
        return orderDB;   
    }


}