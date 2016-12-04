package com.myprojects.billingapp;
/**
 * 
 * @author Kashyap
 * POJO to hold name, price & type of a Menu Item
 */
public class MenuItem {
	private String name;
	private double price;
	private String type;
	
	public MenuItem(String name, double price, String type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
