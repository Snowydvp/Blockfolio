package fr.snowy.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Market implements Serializable {

	private ArrayList<Price> prices;
	private long lastRefreshedTimestamp;

	public Market() {
		this.prices = new ArrayList<>();
	}

	public void putPrices(ArrayList<Price> newPrices) {
		for (Price p : newPrices)
			putPrice(p);
	}

	public void setLastRefreshedTimestamp(long lastRefreshedTimestamp) {
		this.lastRefreshedTimestamp = lastRefreshedTimestamp;
	}

	public void putPrice(Price newPrice) {
		int index;
		if ((index = prices.indexOf(newPrice)) != -1)
			prices.remove(index);
		prices.add(newPrice);
	}

	public ArrayList<Price> getPrices() {
		return prices;
	}

	public long getLastRefreshedTimestamp() {
		return lastRefreshedTimestamp;
	}

}
