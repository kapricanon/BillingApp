package com.myprojects.billingapp;

import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public static void main(String[] args) {
		BillingApp bApp = new BillingApp();
		try {
			for(String item : args) {
				bApp.purchaseItem(item);
			}
			System.out.println("Items Purchased");
			System.out.println("===============");
			for(int idx = 0; idx < bApp.itemsPurchased.size(); idx++) {
				System.out.println(idx+1 + ". " + bApp.itemsPurchased.get(idx));
			}
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
			throw new RuntimeException("Please enter a valid purchase item");
		if(!("Cola".equalsIgnoreCase(itemToPurchase) || "Coffee".equalsIgnoreCase(itemToPurchase)
				|| "Cheese Sandwich".equalsIgnoreCase(itemToPurchase) || "Steak Sandwich".equalsIgnoreCase(itemToPurchase))) 
				throw new RuntimeException("Item not found. Please enter a valid item");
		itemsPurchased.add(itemToPurchase);
		return itemsPurchased;
	}
}
