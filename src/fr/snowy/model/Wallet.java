package fr.snowy.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.Balance;

public class Wallet implements Serializable {
	private Map<Currency, Balance> balance;
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
	
	public Map<Currency, Balance> getBalance() {
		return balance;
	}


	public void addBalance(Map<Currency, Balance> balance) {
		this.balance.putAll(balance);
		this.lastBalanceTimestamp = System.currentTimeMillis();
	}
	
	public void resetBalance()
	{
		this.balance.clear();
	}


	public ArrayList<Order> getOrders() {
		return orders;
	}


	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
		this.lastOrderTimestamp = System.currentTimeMillis();
	}
	

	public long getLastOrderTimestamp() {
		return lastOrderTimestamp;
	}


	public long getLastBalanceTimestamp() {
		return lastBalanceTimestamp;
	}

}
