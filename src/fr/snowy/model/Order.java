package fr.snowy.model;

import java.io.Serializable;

public class Order implements Serializable{
	
	private Currency currencyTo, currencyFrom;
	private String instruction;
	private String type;
	private float price;
	private float quantity;
	private float cost;
	
	public Order(Currency currencyTo, Currency currencyFrom, String instruction, String type, float price,
			float quantity) {
		this.currencyTo = currencyTo;
		this.currencyFrom = currencyFrom;
		this.instruction = instruction;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.cost = quantity * price;
	}

	public Currency getCurrencyTo() {
	    return currencyTo;
	}

	public Currency getCurrencyFrom() {
	    return currencyFrom;
	}

	public String getInstruction() {
	    return instruction;
	}

	public String getType() {
	    return type;
	}

	public float getPrice() {
	    return price;
	}

	public float getQuantity() {
	    return quantity;
	}

	public float getCost() {
	    return cost;
	}
	
	@Override
	public String toString() {
		return "Order [currencyTo=" + currencyTo + ", currencyFrom=" + currencyFrom + ", instruction=" + instruction
				+ ", type=" + type + ", quantity=" + quantity + "@" + price + "]";
	}
	
}
