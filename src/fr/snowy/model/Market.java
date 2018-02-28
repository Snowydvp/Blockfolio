package fr.snowy.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Market implements Serializable {
	
	private ArrayList<Price> prices;

	public Market() {
		this.prices = new ArrayList<>();
	}

	public void setPrices(ArrayList<Price> newPrices)
	{
		this.prices = newPrices;
	}
	
	public ArrayList<Price> getPrices() {
		return prices;
	}


	
}
