package fr.snowy.model;

public class Order {
	
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

	@Override
	public String toString() {
		return "Order [currencyTo=" + currencyTo + ", currencyFrom=" + currencyFrom + ", instruction=" + instruction
				+ ", type=" + type + ", quantity=" + quantity + "@" + price + "]";
	}
	
	

}
