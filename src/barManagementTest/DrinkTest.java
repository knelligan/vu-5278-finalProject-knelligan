package barManagementTest;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import barManagement.Drink;
import barManagement.DrinkAndQuantity;
import barManagement.DrinkFactory;
import barManagement.InventoryDB;
import barManagement.Order;

import java.util.Date;

class DrinkTest {

	@Test
	void testMakeDrink() {
		// create all the valid drinks to test
		ArrayList<String> drinks = createDrinkArray();
		DrinkFactory df = new DrinkFactory();

		// test red wine drink-------------------------------------
		Drink testWine = df.makeDrink(drinks.get(0));

		// test name
		assertTrue(testWine.getName().equals("Red wine"));

		// test size
		assertTrue(testWine.getSize() == 5.0);

		// test price
		assertTrue(testWine.getPrice() == 12.00);

		// test glass
		assertTrue(testWine.getGlass().equals("Red Wine"));

		// test vodka drink-------------------------------------
		Drink testVodka = df.makeDrink(drinks.get(17));

		// test name
		assertTrue(testVodka.getName().equals("Vodka"));

		// test size
		assertTrue(testVodka.getSize() == 1.5);

		// test price
		assertTrue(testVodka.getPrice() == 2.50);

		// test glass
		assertTrue(testVodka.getGlass().equals("Shot"));

		// test coke drink-------------------------------------
		Drink testCoke = df.makeDrink(drinks.get(1));

		// test name
		assertTrue(testCoke.getName().equals("Coke"));

		// test size
		assertTrue(testCoke.getSize() == 6.0);

		// test price
		assertTrue(testCoke.getPrice() == 2.00);

		// test glass
		assertTrue(testCoke.getGlass().equals("Pint"));

		// test ice
		assertTrue(testCoke.getIce().equals("Ice cubes"));

		// test small guiness draft drink------------------------
		Drink testGuiness = df.makeDrink(drinks.get(11));

		// test name
		assertTrue(testGuiness.getName().equals("Small Guiness"));

		// test size
		assertTrue(testGuiness.getSize() == 12.0);

		// test price
		assertTrue(testGuiness.getPrice() == 7.00);

		// test glass
		assertTrue(testGuiness.getGlass().equals("Mug"));

		// test product
		assertTrue(testGuiness.getProductName().equals("Guiness"));

		// test large coors light draft drink------------------------
		Drink testCoors = df.makeDrink(drinks.get(15));

		// test name
		assertTrue(testCoors.getName().equals("Large Coors Light"));

		// test size
		assertTrue(testCoors.getSize() == 20.0);

		// test price
		assertTrue(testCoors.getPrice() == 7.00);

		// test glass
		assertTrue(testCoors.getGlass().equals("Pilsner"));

		// test product
		assertTrue(testCoors.getProductName().equals("Coors Light"));

	}

	ArrayList<String> createDrinkArray() {

		ArrayList<String> drinkNames = new ArrayList<String>();

		drinkNames.add("Red wine");
		drinkNames.add("Coke");
		drinkNames.add("Sprite");
		drinkNames.add("Ginger Ale");
		drinkNames.add("Orange Juice");
		drinkNames.add("Cranberry Juice");
		drinkNames.add("Pineapple Juice");
		drinkNames.add("Lime Juice");
		drinkNames.add("Bud Light");
		drinkNames.add("Corona");
		drinkNames.add("Heineken");
		drinkNames.add("Small Guiness");
		drinkNames.add("Small Coors Light");
		drinkNames.add("Small Sam Adams");
		drinkNames.add("Large Guiness");
		drinkNames.add("Large Coors Light");
		drinkNames.add("Large Sam Adams");
		drinkNames.add("Vodka");
		drinkNames.add("Bourbon");
		drinkNames.add("Tequila");
		drinkNames.add("Gin");
		drinkNames.add("Whiskey");
		drinkNames.add("Rum");
		drinkNames.add("Cordial");
		drinkNames.add("Vermouth");

		return drinkNames;
	}

	ArrayList<Integer> createIntegerArray() {

		ArrayList<Integer> drinkNumbers = new ArrayList<Integer>();

		for (int i = 1; i <= 25; i++) {
			drinkNumbers.add(i);
		}

		return drinkNumbers;
	}

	@Test
	void testMixedDrink() {
		// create all the valid drinks to test

		DrinkFactory df = new DrinkFactory();

		// test martini drink-------------------------------------
		Drink testMartini = df.makeDrink("Gin Martini");

		// test name
		assertTrue(testMartini.getName().equals("Gin Martini"));

		// test size
		assertTrue(testMartini.getSize() == 3.0);

		// test price
		assertTrue(testMartini.getPrice() == 10.99);

		// test glass
		assertTrue(testMartini.getGlass().equals("Martini"));

		// test ice
		assertTrue(testMartini.getIce().equals("Strain ice out"));

		// test garnish
		assertTrue(testMartini.getGarnish().equals("Olive"));
	}

	@Test
	void testDrinkAndQuantity() {
		ArrayList<String> drinks = createDrinkArray();
		DrinkFactory df = new DrinkFactory();

		DrinkAndQuantity dq = new DrinkAndQuantity();

		// test creating a drink and quantity with 5 red wines
		Drink redWine = df.makeDrink(drinks.get(0));
		dq.setDrink(redWine);
		dq.setQuantity(5);
		assertTrue(dq.getDrink().getName().equals("Red wine"));
		assertTrue(dq.getQuantity() == 5);

		// test creating a drink and quantity with 3 cokes
		Drink coke = df.makeDrink(drinks.get(1));
		dq.setDrink(coke);
		dq.setQuantity(3);
		assertTrue(dq.getDrink().getName().equals("Coke"));
		assertTrue(dq.getQuantity() == 3);

		// test creating a drink and quantity with 100 martinis
		Drink testMartini = df.makeDrink("Gin Martini");
		dq.setDrink(testMartini);
		dq.setQuantity(100);
		assertTrue(dq.getDrink().getName().equals("Gin Martini"));
		assertTrue(dq.getQuantity() == 100);

	}

	@Test
	void testOrder() {
		// set up order
		Integer orderNum = 15;
		String bartenderName = "Ken";
		String customerName = "Kate";
		Date date = new Date();
		ArrayList<String> drinkList = createDrinkArray();
		ArrayList<Integer> quantities = createIntegerArray();
		InventoryDB idb = new InventoryDB();
		Order ord = new Order(orderNum, bartenderName, customerName, date, drinkList, quantities, idb);

		// check order info
		int ordNumTest = ord.getOrderNumber();
		assertTrue(ordNumTest == 15);

		// check bartender info
		String bartenderNameTest = ord.getBartenderName();
		assertTrue(bartenderNameTest.equals("Ken"));

		// check customer info
		String customerNameTest = ord.getCustomerName();
		assertTrue(customerNameTest.equals("Kate"));

		// check payment info
		boolean paidTest = ord.getPayment();
		assertTrue(paidTest == false);

		// check drinks
		ArrayList<DrinkAndQuantity> dq = ord.getDrinks();

		Drink redWine = dq.get(0).getDrink();
		assertTrue(redWine.getName().equals("Red wine"));
		assertTrue(dq.get(0).getQuantity() == 1);

		Drink coke = dq.get(1).getDrink();
		assertTrue(coke.getName().equals("Coke"));
		assertTrue(dq.get(1).getQuantity() == 2);

		Drink vermouth = dq.get(24).getDrink();
		assertTrue(vermouth.getName().equals("Vermouth"));
		assertTrue(dq.get(24).getQuantity() == 25);

	}

	@Test
	void testOrderDB() {

	}
}
