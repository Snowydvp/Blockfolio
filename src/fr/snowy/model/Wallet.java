package fr.snowy.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class Wallet implements Serializable {
	private HashMap<Currency, Float> balance;
	private ArrayList<Order> orders;
	private long lastOrderTimestamp, lastBalanceTimestamp;
	
	public Wallet()
	{
		this.balance = new HashMap<>();
		this.orders = new ArrayList<>();
	}
	

	@Override
	public String toString() {
		return "Wallet [wallet dynamic = " + balance + "]";
	}
	
	public HashMap<Currency, Float> getBalance() {
		return balance;
	}


	public void setBalance(HashMap<Currency, Float> balance) {
		this.balance = balance;
	}


	public ArrayList<Order> getOrders() {
		return orders;
	}


	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	

	public long getLastOrderTimestamp() {
		return lastOrderTimestamp;
	}


	public long getLastBalanceTimestamp() {
		return lastBalanceTimestamp;
	}

}
