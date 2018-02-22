package fr.snowy.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Market {
	
	private ArrayList<Price> prices;

	public Market() {
		this.prices = new ArrayList<>();
	}

	public void putPrice(Price newPrice)
	{
		int index;
		if((index = prices.indexOf(newPrice)) != -1)
			prices.remove(index);
		prices.add(newPrice);
	}
	
	public ArrayList<Price> getPrices() {
		return prices;
	}


	
}
