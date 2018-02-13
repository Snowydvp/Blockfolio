package fr.snowy.model;

import java.util.Arrays;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletParser {
    
    private String[] error;
    private HashMap<String, String> result;
    
    @JsonCreator
    public WalletParser(@JsonProperty("error") String[] error, @JsonProperty("result")HashMap result)
    {
	this.error = error;
	this.result = result;
    }
    
    public void setError(String[] error)
    {
	this.error = error;
    }
    
    public String[] getError()
    {
	return error;
    }

    public HashMap getResult() {
        return result;
    }

    public void setResult(HashMap result) {
        this.result = result;
    }
   
    public Wallet convertToKraken()
	{
		Wallet wallet = new Wallet();
		String valueStr;
		Currency currency;
		Float value;
		
		for(String currencyStr : this.result.keySet())
		{
			valueStr = result.get(currencyStr);
			currency = Utils.convertCurrencyFromKraken(currencyStr);
			value = new Float(valueStr);
			wallet.getWalletContent().put(currency, value);
		}
		return wallet;
	}
    @Override
    public String toString() {
	return "Wallet [error=" + Arrays.toString(error) + ", result=" + result + "]";
    }
    
    

    
}
