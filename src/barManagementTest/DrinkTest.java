package barManagementTest;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import barManagement.Drink;
import barManagement.DrinkFactory;

class DrinkTest {

	@Test
	void testMakeDrink() {
		// create all the valid drinks to test
		ArrayList<String> drinks = createDrinkArray("correct");
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

		// test Large Coors Light draft drink------------------------
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

	ArrayList<String> createDrinkArray(String type) {

		ArrayList<String> drinkNames = new ArrayList<String>();
		if (type.equals("correct")) {
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
		} else {
			drinkNames.add("Red Wine");
			drinkNames.add("Coke");
			drinkNames.add("Milk");
			drinkNames.add("Tea");
			drinkNames.add("");
		}
		return drinkNames;
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
}
