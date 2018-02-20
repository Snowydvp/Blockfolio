package fr.snowy.model.kraken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import fr.snowy.model.Currency;
import fr.snowy.model.Order;
import fr.snowy.model.Price;

public class KrakenUtils {
    
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

	public static HashMap<Currency, Float> convertToKrakenWallet(KrakenParser response) {
		HashMap<Currency, Float> balance = new HashMap<>();
		String valueStr;
		Currency currency;
		Float value;

		for (String currencyStr : (Set<String>) response.getResult().keySet()) {
			valueStr = (String) response.getResult().get(currencyStr);
			currency = convertCurrencyFromKraken(currencyStr);
			value = new Float(valueStr);
			balance.put(currency, value);
		}
		return balance;
	}

	public static ArrayList<Order> convertToKrakenOrders(KrakenParser response) {
		ArrayList<Order> orders = new ArrayList<>();
		Order order;
		HashMap<String, String> orderInfos;
		String[] krakenOrder;
		Currency[] pair;
		float price;
		// System.out.println(response.getClosed());

		for (String idOrder : response.getClosed().keySet()) {
			orderInfos = (HashMap<String, String>) response.getClosed().get(idOrder).get("descr");
			price = Float.parseFloat((String) response.getClosed().get(idOrder).get("price"));
			krakenOrder = orderInfos.get("order").split(" ");
			pair = parseCurrencies(krakenOrder[2]);
			order = new Order(pair[0], pair[1], krakenOrder[0], krakenOrder[4], price,
					Float.parseFloat(krakenOrder[1]));
			orders.add(order);
		}
		return orders;
	}

	public static Currency[] parseCurrencies(String pair) {
		Currency[] currencies = new Currency[2];
		currencies[0] = convertCurrencyFromKraken(pair.substring(0, 4));
		currencies[1] = convertCurrencyFromKraken(pair.substring(4));
		return currencies;
	}
	
	public static Price parseFromKrakenPrice(KrakenParser response)
	{
	    String pairStr;
	    double bidValue, askValue;
	    Currency[] pair;
	    ArrayList<String> ask, bid;
	    
	    pairStr = (String) response.getResult().keySet().toArray()[0];
	    HashMap prices = (HashMap)(response.getResult().get(pairStr));
	    ask = (ArrayList<String>) prices.get("a");
	    bid = (ArrayList<String>) prices.get("b");
	    
	    pair = parseCurrencies(pairStr); 
	    Price price = new Price(pair[0], pair[1], computeMarketValue(ask, bid));
	    return price;
	}
	
	public static double computeMarketValue(ArrayList<String> ask, ArrayList<String> bid)
	{
		double askPrice = Double.parseDouble(ask.get(0));
		double bidPrice = Double.parseDouble(bid.get(0));
		int askCoeff = Integer.parseInt(ask.get(1));
		int bidCoeff = Integer.parseInt(bid.get(1));
		
		return ( (askCoeff * askPrice) + (bidPrice * bidCoeff) ) / (askCoeff + bidCoeff);
		
	}

}
