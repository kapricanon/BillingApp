package com.myprojects.billingapp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;

import com.myprojects.billingapp.CodeConstants;
import com.myprojects.billingapp.MenuItem;


/**
 * 
 * @author Kashyap
 * 
 * Billing Application written to generate a bill including a service charge for Café X 
 * so the customer does not have to work out how much to tip.
 * 
 */

public class BillingApp {

	//Holds the list of items purchased
	private Map<String, MenuItem> itemsPurchased = new HashMap<String, MenuItem>();
	//Holds a map of items, their price & type that are in Cafe X's menu list
	private static Map<String, MenuItem> menuItemMap = new HashMap<String, MenuItem>();
	private double totalCharge = 0;
	
	//Initialise the menuItemMap to hold the list of items which are in Cafe X's menu list
	//In real life scenario this will be done either by spring injection or read from db on app startup
	static {
		menuItemMap.put(CodeConstants.menuItemCola, new MenuItem(CodeConstants.menuItemCola, 0.50, CodeConstants.coldDrink));
		menuItemMap.put(CodeConstants.menuItemCoffee, new MenuItem(CodeConstants.menuItemCoffee, 1.00, CodeConstants.hotDrink));
		menuItemMap.put(CodeConstants.menuItemCheeseSandwich, new MenuItem(CodeConstants.menuItemCheeseSandwich, 2.00, CodeConstants.coldFood));
		menuItemMap.put(CodeConstants.menuItemSteakSandwich, new MenuItem(CodeConstants.menuItemSteakSandwich, 4.50, CodeConstants.hotFood));
	}
	
	public static void main(String[] args) {
		BillingApp bApp = new BillingApp();
		int idx = 0;
		try {
			for(String item : args) {
				bApp.purchaseItem(item);
			}
			System.out.println("\r\n===============");
			System.out.println("Items Purchased");
			System.out.println("===============");
			System.out.printf("%5s %-18s %s\n", "Item", "", "Price");
			System.out.println();
			for(MenuItem menuItem : bApp.itemsPurchased.values()) {
				System.out.printf("%2d. %-20s £%.2f\n", ++idx, menuItem.getName(), menuItem.getPrice());
			}
			System.out.println("--------------------");
			System.out.printf("%5s %-13s £%.2f\n", "Sub Total ", "", bApp.totalCharge);
			
			System.out.println("--------------------");
			System.out.printf("%5s %-10s £%.2f\n", "Service Charge", "", bApp.calculateServiceCharge());
			
			System.out.println("--------------------");
			System.out.printf("%5s %-12s £%.2f\n", "Total Charge", "", bApp.totalCharge + bApp.calculateServiceCharge());
			System.out.println("--------------------");
		}catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param itemToPurchase
	 * @return Map of items in the purchase list
	 * @exception RuntimeException
	 * 
	 */
	public Map<String, MenuItem> purchaseItem(String itemToPurchase) {
		if(StringUtils.isBlank(itemToPurchase)) 
			throw new RuntimeException(CodeConstants.pleaseEnterValidItemMessage);
		if(!menuItemMap.containsKey(itemToPurchase))
				throw new RuntimeException(CodeConstants.oneOrMoreItemInvalidMessage);
		MenuItem menuItem = menuItemMap.get(itemToPurchase);
		itemsPurchased.put(menuItem.getName(), new MenuItem(menuItem.getName(), menuItem.getPrice(), menuItem.getType()));
		totalCharge += getPrice(itemToPurchase);
		return itemsPurchased;
	}

	/**
	 * 
	 * @param getPrice
	 * @return price of the item to purchase
	 */
	public double getPrice(String itemToPurchase) {
		if(StringUtils.isBlank(itemToPurchase)) 
			return 0;
		if(menuItemMap.containsKey(itemToPurchase))
			return menuItemMap.get(itemToPurchase).getPrice();
		else 
			return 0;
	}
	
	public double getTotalCharge() {
		return totalCharge;
	}

	/**
	 * 
	 * @return the service charge based on the items in purchase list i.e. cold food items only will incur 10% service charge
	 * whereas a hot food item in the list will incur 20% or £20 service charge which ever is minimum
	 */
	public double calculateServiceCharge() {
		boolean foodItem = false;
		Predicate<MenuItem> coldFoodPredicate = e -> e.getType().equalsIgnoreCase(CodeConstants.coldFood);
		Predicate<MenuItem> hotFoodPredicate = e -> e.getType().equalsIgnoreCase(CodeConstants.hotFood);
		foodItem = itemsPurchased.values().stream().anyMatch(coldFoodPredicate);
		if(itemsPurchased.values().stream().anyMatch(hotFoodPredicate))
			return (totalCharge * 20.00 / 100.0 > 20.00) ? 20.00 : totalCharge * 20.00 / 100.0;
		if(foodItem)
			return totalCharge * 10.0 / 100.0;
		return 0;
	}
}
