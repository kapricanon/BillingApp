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
		List<String> purchasedItems = bApp.purchaseItem(CodeConstants.menuItemCola);
		assertTrue(purchasedItems.get(0).equals(CodeConstants.menuItemCola));
	}
	
	@Test
	public void purchaseTwoValidItems() {
		BillingApp bApp = new BillingApp();
		List<String> purchasedItems = bApp.purchaseItem(CodeConstants.menuItemCola);
		bApp.purchaseItem(CodeConstants.menuItemCoffee);
		assertTrue(purchasedItems.get(0).equals(CodeConstants.menuItemCola));
		assertTrue(purchasedItems.get(1).equals(CodeConstants.menuItemCoffee));
	}
	
	@Test
	public void purchaseMultipleValidItems() {
		BillingApp bApp = new BillingApp();
		List<String> purchasedItems = bApp.purchaseItem(CodeConstants.menuItemCola);
		bApp.purchaseItem(CodeConstants.menuItemCoffee);
		bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		bApp.purchaseItem(CodeConstants.menuItemSteakSandwich);
		assertTrue(purchasedItems.get(0).equals(CodeConstants.menuItemCola));
		assertTrue(purchasedItems.get(1).equals(CodeConstants.menuItemCoffee));
		assertTrue(purchasedItems.get(2).equals(CodeConstants.menuItemCheeseSandwich));
		assertTrue(purchasedItems.get(3).equals(CodeConstants.menuItemSteakSandwich));
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
		double price = bApp.getPrice(CodeConstants.menuItemCola);
		assertEquals(price, 0.50, DELTA);
	}
	
	@Test
	public void findPriceForCoffee() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice(CodeConstants.menuItemCoffee);
		assertEquals(price, 1.00, DELTA);
	}
	
	@Test
	public void findPriceForCheeseSandwich() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice(CodeConstants.menuItemCheeseSandwich);
		assertEquals(price, 2.00, DELTA);
	}
	
	@Test
	public void findPriceForSteakSandwich() {
		BillingApp bApp = new BillingApp();
		double price = bApp.getPrice(CodeConstants.menuItemSteakSandwich);
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
		bApp.purchaseItem(CodeConstants.menuItemCola);
		bApp.purchaseItem(CodeConstants.menuItemCoffee);
		bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		assertEquals(bApp.getTotalCharge(), 3.50, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfZeroItemsThenServiceChangeShouldBeZero() {
		BillingApp bApp = new BillingApp();
		assertEquals(bApp.calculateServiceCharge(), 0.00, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeForNonFoodItemsThenServiceChangeShouldBeZero() {
		BillingApp bApp = new BillingApp();
		bApp.purchaseItem(CodeConstants.menuItemCola);
		bApp.purchaseItem(CodeConstants.menuItemCoffee);
		assertEquals(bApp.calculateServiceCharge(), 0.00, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfFoodItemAndNonFoodItemThenServiceChargeShouldBe10Percent() {
		BillingApp bApp = new BillingApp();
		bApp.purchaseItem(CodeConstants.menuItemCola);
		bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		assertEquals(bApp.calculateServiceCharge(), 0.25, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfFoodItemsThenServiceChargeShouldBe10Percent() {
		BillingApp bApp = new BillingApp();
		bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		assertEquals(bApp.calculateServiceCharge(), 0.2, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfHotFoodItemsThenServiceChargeShouldBe20Percent() {
		BillingApp bApp = new BillingApp();
		bApp.purchaseItem(CodeConstants.menuItemSteakSandwich);
		bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		assertEquals(bApp.calculateServiceCharge(), 1.30, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfHotFoodItemsAndDrinksThenServiceChargeShouldBe20Percent() {
		BillingApp bApp = new BillingApp();
		bApp.purchaseItem(CodeConstants.menuItemSteakSandwich);
		bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		bApp.purchaseItem(CodeConstants.menuItemCoffee);
		assertEquals(bApp.calculateServiceCharge(), 1.50, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfHotFoodLimitTheChargeTo20Pounds() {
		BillingApp bApp = new BillingApp();
		for(int idx = 0; idx < 32; idx++) {
			bApp.purchaseItem(CodeConstants.menuItemSteakSandwich);
		}
		assertEquals(bApp.calculateServiceCharge(), 20.00, DELTA);
	}
	
	@Test
	public void whenCalculateServiceChargeOfColdFoodDoNotLimitTheChargeTo20Pounds() {
		BillingApp bApp = new BillingApp();
		for(int idx = 0; idx < 200; idx++) {
			bApp.purchaseItem(CodeConstants.menuItemCheeseSandwich);
		}
		assertEquals(bApp.calculateServiceCharge(), 40.00, DELTA);
	}
	
}
