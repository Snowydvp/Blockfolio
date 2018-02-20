package fr.snowy.model;

public class Price {
    
    private Currency cryptoTo, currencyFrom;
    private Float value;
    
    
    
    public Price(Currency cryptoTo, Currency currencyFrom, Float value) {
	this.cryptoTo = cryptoTo;
	this.currencyFrom = currencyFrom;
	this.value = value;
    }
    public Currency getCryptoTo() {
        return cryptoTo;
    }
    public Currency getCurrencyFrom() {
        return currencyFrom;
    }
    public Float getValue() {
        return value;
    }
    
    

}
