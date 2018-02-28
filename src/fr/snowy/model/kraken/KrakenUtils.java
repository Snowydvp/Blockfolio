package fr.snowy.model.kraken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import fr.snowy.model.Crypto;
import fr.snowy.model.Currency;
import fr.snowy.model.Fiat;
import fr.snowy.model.Order;
import fr.snowy.model.Price;

public class KrakenUtils {

	public static Currency convertCurrencyFromKraken(String krakenCurrency) {
		if (krakenCurrency.length() == 4)
			krakenCurrency = krakenCurrency.substring(1);
		// if(krakenCurrency.length() != 4)
		// throw new Exception("Bad kraken Currency (" + krakenCurrency + ')');
		Currency curr = Crypto.getByName(krakenCurrency);
		if(curr == null)
			curr = Fiat.getByName(krakenCurrency);
		return curr;
	}

	public static String convertCurrencyToKraken(Currency Currency) {
	char prefix;
	if(Currency instanceof Fiat)
		prefix = 'Z';
	else
		prefix = 'X';

	return prefix + Currency.toString();
    }

	public static Currency[] parseCurrencies(String pair) {
		int delimit = pair.length() / 2;
		Currency[] currencies = new Currency[2];
		currencies[0] = convertCurrencyFromKraken(pair.substring(0, delimit));
		currencies[1] = convertCurrencyFromKraken(pair.substring(delimit));
		return currencies;
	}

	public static double computeMarketValue(ArrayList<String> ask, ArrayList<String> bid) {
		double askPrice = Double.parseDouble(ask.get(0));
		double bidPrice = Double.parseDouble(bid.get(0));
		int askCoeff = Integer.parseInt(ask.get(1));
		int bidCoeff = Integer.parseInt(bid.get(1));

		return ((askCoeff * askPrice) + (bidPrice * bidCoeff)) / (askCoeff + bidCoeff);
	}

}
