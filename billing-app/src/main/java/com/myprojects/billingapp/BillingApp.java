package com.myprojects.billingapp;

import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author Kashyap
 * Billing Application written to generate a bill including a service charge for Café X 
 * so the customer does not have to work out how much to tip.
 * 
 * Step 01 - Create a list of items purchased by a customer
 */

public class BillingApp {

	//Holds the list of items purchased
	private List<String> itemsPurchased = new ArrayList<String>();
	//Holds a map of items, their price & type
	private Map<String, Double> menuItemMap = new HashMap<String, Double>();
	private double totalCharge = 0;
	
	public BillingApp() {
		menuItemMap.put(CodeConstants.menuItemCola, 0.50);
		menuItemMap.put(CodeConstants.menuItemCoffee, 1.00);
		menuItemMap.put(CodeConstants.menuItemCheeseSandwich, 2.00);
		menuItemMap.put(CodeConstants.menuItemSteakSandwich, 4.50);
	}

	public static void main(String[] args) {
		BillingApp bApp = new BillingApp();
		
		try {
			for(String item : args) {
				bApp.purchaseItem(item);
			}
			System.out.println("\r\n===============");
			System.out.println("Items Purchased");
			System.out.println("===============");
			System.out.printf("%5s %-18s %s\n", "Item", "", "Price");
			System.out.println();
			for(int idx = 0; idx < bApp.itemsPurchased.size(); idx++) {
				System.out.printf("%2d. %-20s £%.2f\n", idx+1, bApp.itemsPurchased.get(idx), bApp.getPrice(bApp.itemsPurchased.get(idx)) );
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
	 * @return list of items in the purchase list
	 * @exception RuntimeException
	 * 
	 */
	public List<String> purchaseItem(String itemToPurchase) {
		if(StringUtils.isBlank(itemToPurchase)) 
			throw new RuntimeException(CodeConstants.pleaseEnterValidItemMessage);
		if(!menuItemMap.containsKey(itemToPurchase))
				throw new RuntimeException(CodeConstants.oneOrMoreItemInvalidMessage);
		itemsPurchased.add(itemToPurchase);
		totalCharge += getPrice(itemToPurchase);
		return itemsPurchased;
	}

	public double getPrice(String itemToPurchase) {
		if(StringUtils.isBlank(itemToPurchase)) 
			return 0;
		if(menuItemMap.containsKey(itemToPurchase))
			return menuItemMap.get(itemToPurchase);
		else 
			return 0;
	}
	
	public double getTotalCharge() {
		return totalCharge;
	}

	public double calculateServiceCharge() {
		boolean foodItem = false;
		for(String item : itemsPurchased) {
			if(item.equalsIgnoreCase(CodeConstants.menuItemCheeseSandwich)) {
				foodItem = true;
			}
			if (item.equalsIgnoreCase(CodeConstants.menuItemSteakSandwich)) {
				return (totalCharge * 20.00 / 100.0 > 20.00) ? 20.00 : totalCharge * 20.00 / 100.0;
			}
		}
		if(foodItem)
			return totalCharge * 10.0 / 100.0;
		else
			return 0;
	}
}
