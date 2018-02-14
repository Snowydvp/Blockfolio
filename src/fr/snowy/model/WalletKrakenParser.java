package fr.snowy.model;

import java.util.HashMap;

public class WalletKrakenParser extends WalletParser{
	

	public WalletKrakenParser(String[] error, HashMap result) {
		super(error, result);
	}

	@Override
	public Wallet convertToKraken()
	{
		Wallet wallet = new Wallet();
		String valueStr;
		Currency currency;
		Float value;
		
		for(String currencyStr : super.result.keySet())
		{
			valueStr = super.result.get(currencyStr);
			currency = Utils.convertCurrencyFromKraken(currencyStr);
			value = new Float(valueStr);
			wallet.getWalletContent().put(currency, value);
		}
		return wallet;
	}
    
}
