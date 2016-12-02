/**
 * 
 */
package com.myprojects.billingapp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author User
 *
 */
public class BillingAppTest {

	/**
	 * @throws java.lang.Exception
	 */
	private static final double DELTA = 1e-15;

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void purchaseOneValidItem() {
		BillingApp bApp = new BillingApp();
		List<String> purchasedItems = bApp.purchaseItem("Cola");
		assertTrue(purchasedItems.get(0).equals("Cola"));
	}
	
	@Test
	public void purchaseTwoValidItems() {
		BillingApp bApp = new BillingApp();
		List<String> purchasedItems = bApp.purchaseItem("Cola");
		bApp.purchaseItem("Coffee");
		assertTrue(purchasedItems.get(0).equals("Cola"));
		assertTrue(purchasedItems.get(1).equals("Coffee"));
	}
	
	@Test
	public void purchaseMultipleValidItems() {
		BillingApp bApp = new BillingApp();
		List<String> purchasedItems = bApp.purchaseItem("Cola");
		bApp.purchaseItem("Coffee");
		bApp.purchaseItem("Cheese Sandwich");
		bApp.purchaseItem("Steak Sandwich");
		assertTrue(purchasedItems.get(0).equals("Cola"));
		assertTrue(purchasedItems.get(1).equals("Coffee"));
		assertTrue(purchasedItems.get(2).equals("Cheese Sandwich"));
		assertTrue(purchasedItems.get(3).equals("Steak Sandwich"));
	}
	
	@Test
	public void whenNullValueIsUsedToPurchaseItemsThenRunTimeExceptionIsThrown() {
		RuntimeException exception = null;
		BillingApp bApp = new BillingApp();
		try {
			bApp.purchaseItem(null);
		} catch (RuntimeException e) {
			exception = e;
		}
		Assert.assertNotEquals(exception, null);
	}
	
	@Test
	public void whenAnEmptyStringIsUsedToPurchaseItemsThenRunTimeExceptionIsThrown() {
		RuntimeException exception = null;
		BillingApp bApp = new BillingApp();
		try {
			bApp.purchaseItem("");
		} catch (RuntimeException e) {
			exception = e;
		}
		Assert.assertNotEquals(exception, null);
	}
	
	@Test
	public void whenStringWithOnlySpacesIsUsedToPurchaseItemsThenRunTimeExceptionIsThrown() {
		RuntimeException exception = null;
		BillingApp bApp = new BillingApp();
		try {
			bApp.purchaseItem("  ");
		} catch (RuntimeException e) {
			exception = e;
		}
		Assert.assertNotEquals(exception, null);
	}
	
	@Test
	public void whenAnInvalidItemIsUsedThenRunTimeExceptionIsThrown() {
		RuntimeException exception = null;
		BillingApp bApp = new BillingApp();
		try {
			bApp.purchaseItem("Item wont exist ever");
		} catch (RuntimeException e) {
			exception = e;
		}
		Assert.assertNotEquals(exception, null);
	}
	
	@Test
	public void findPriceForCola() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice("Cola");
		assertEquals(price, 0.50, DELTA);
	}
	
	@Test
	public void findPriceForCoffee() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice("Coffee");
		assertEquals(price, 1.00, DELTA);
	}
	
	@Test
	public void findPriceForCheeseSandwich() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice("Cheese Sandwich");
		assertEquals(price, 2.00, DELTA);
	}
	
	@Test
	public void findPriceForSteakSandwich() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice("Steak Sandwich");
		assertEquals(price, 4.5, DELTA);
	}
	
	@Test
	public void whenAnInvalidItemNameIsUsedToFindPriceThenExpectZero() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice("Item wont exist ever");
		assertEquals(price, 0, DELTA);
	}
	
	@Test
	public void whenEmptyStringIsUsedAsItemNameToFindPriceThenExpectZero() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice("  ");
		assertEquals(price, 0, DELTA);
	}
	
	@Test
	public void whenNullValueIsUsedAsItemNameToFindPriceThenExpectZero() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice(null);
		assertEquals(price, 0, DELTA);
	}
	
	@Test
	public void calculateTotalPriceOfThreeItems() {
		BillingApp bApp = new BillingApp();
		bApp.purchaseItem("Cola");
		bApp.purchaseItem("Coffee");
		bApp.purchaseItem("Cheese Sandwich");
		assertEquals(bApp.getTotalCharge(), 3.50, DELTA);
	}
		
}
