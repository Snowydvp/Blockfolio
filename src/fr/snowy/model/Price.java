package fr.snowy.model;

import java.util.Comparator;

public class Price implements Comparable<Price>{
    
    private Currency currencyTo, currencyFrom;
    private double value;
    
    
    public Price(Currency cryptoTo, Currency currencyFrom, double value) {
	this.currencyTo = cryptoTo;
	this.currencyFrom = currencyFrom;
	this.value = value;
    }
    
	@Override
	public int compareTo(Price o) { // %
		return (int) ((o.getValue() - this.getValue()) * 100 / o.getValue());
	}
	
	@Override
	public boolean equals(Object  o)
	{
		if(o instanceof Price)
		{
			Price otherPrice = (Price) o;
			return (this.currencyFrom == otherPrice.currencyFrom && this.currencyTo == otherPrice.currencyTo);
		}
		return false;
		
	}
	
	  
    public Currency getCurrencyTo() {
        return currencyTo;
    }
    public Currency getCurrencyFrom() {
        return currencyFrom;
    }
    public double getValue() {
        return value;
    }
    

}
