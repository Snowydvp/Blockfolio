package fr.snowy.model;

public class Utils {
	
	public static Currency convertCurrencyFromKraken(String krakenCurrency)
	{
		Currency cur = null;
//		if(krakenCurrency.length() != 4)
//			throw new Exception("Bad kraken currency (" + krakenCurrency + ')');
		return Currency.getByName(krakenCurrency.substring(1));
	}

	public static String convertCurrencyToKraken(Currency currency)
	{
		char prefix;
		switch (currency) {
		case EUR:
			prefix = 'Z';
			break;

		default:
			prefix = 'X';
			break;
		}

		return prefix + currency.toString();
	}
}
