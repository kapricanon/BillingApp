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
			bApp.purchaseItem("Tomato and Cheese Sandwich");
		} catch (RuntimeException e) {
			exception = e;
		}
		Assert.assertNotEquals(exception, null);
	}
	

}
