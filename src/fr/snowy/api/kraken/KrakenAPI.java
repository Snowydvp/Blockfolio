package fr.snowy.api.kraken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.self.kraken.api.KrakenApi;
import edu.self.kraken.api.KrakenApi.Method;
import fr.snowy.model.Crypto;
import fr.snowy.model.Currency;
import fr.snowy.model.Order;
import fr.snowy.model.Price;
import fr.snowy.model.kraken.KrakenParser;
import fr.snowy.model.kraken.KrakenUtils;

public class KrakenAPI {

	private String key, secret;

	private KrakenApi api;

	public KrakenAPI() {
		File keys = new File("resources/api.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(keys);
			key = scanner.nextLine();
			secret = scanner.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.api = new KrakenApi();
		this.api.setKey(key);
		this.api.setSecret(secret);
	}

	// TODO move KrakenUtils methods here

	public ArrayList<Order> queryOrders(HashMap<String, String> parameters) {
		ArrayList<Order> orders = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		String response;
		KrakenParser krakenParser;

		try {
			Order order;
			HashMap<String, String> orderInfos;
			String[] krakenOrder;
			Currency[] pair;
			float price;
			
			// TODO try orders info
			response = this.api.queryPrivate(Method.CLOSED_ORDERS, parameters);
			krakenParser = mapper.readValue(response, KrakenParser.class);
			
			for (String idOrder : krakenParser.getClosed().keySet()) {
				orderInfos = (HashMap<String, String>) krakenParser.getClosed().get(idOrder).get("descr");
				price = Float.parseFloat((String) krakenParser.getClosed().get(idOrder).get("price"));
				krakenOrder = orderInfos.get("order").split(" ");
				pair = KrakenUtils.parseCurrencies(krakenOrder[2]);
				order = new Order(pair[0], pair[1], krakenOrder[0], krakenOrder[4], price,
						Float.parseFloat(krakenOrder[1]));
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;

	}

	public HashMap<Currency, Float> queryBalance(Map<String, String> parameters) {
		String response;
		ObjectMapper mapper = new ObjectMapper();
		KrakenParser krakenParser;
		HashMap<Currency, Float> balance = new HashMap<>();

		try {
			String valueStr;
			Currency currency;
			
			response = this.api.queryPrivate(Method.TRADE_BALANCE, parameters);
			krakenParser = mapper.readValue(response, KrakenParser.class);
			
			for (String currencyStr : (Set<String>) krakenParser.getResult().keySet()) {
				valueStr = (String) krakenParser.getResult().get(currencyStr);
				currency = KrakenUtils.convertCurrencyFromKraken(currencyStr);
				balance.put(currency, Float.parseFloat(valueStr));
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return balance;
	}

	public ArrayList<Price> queryPrices(Map<String, String> parameters) {
		String response;
		ObjectMapper mapper = new ObjectMapper();
		KrakenParser krakenParser;
		ArrayList<Price> prices = new ArrayList<>();

		try {
			Currency[] pair;
			ArrayList<String> ask, bid;
			
			// response = this.krakenApi.queryPublic(Method.TICKER, input);
			response = "{\"error\":[],\"result\":{\"XXBTZEUR\":{\"a\":[\"9124.90000\",\"1\",\"1.000\"],\"b\":[\"9120.00000\",\"4\",\"4.000\"],\"c\":[\"9120.00000\",\"0.15460000\"],\"v\":[\"168.16693995\",\"9985.28473015\"],\"p\":[\"9070.43189\",\"8860.91132\"],\"t\":[737,46440],\"l\":[\"9009.10000\",\"8364.00000\"],\"h\":[\"9124.90000\",\"9124.90000\"],\"o\":\"9017.70000\"}}}";
			krakenParser = mapper.readValue(response, KrakenParser.class);

			for (String pairStr : (Set<String>) krakenParser.getResult().keySet()) {
				HashMap hashPrice = (HashMap) (krakenParser.getResult().get(pairStr));
				ask = (ArrayList<String>) hashPrice.get("a");
				bid = (ArrayList<String>) hashPrice.get("b");

				pair = KrakenUtils.parseCurrencies(pairStr);
				Price price = new Price((Crypto) pair[0], pair[1], KrakenUtils.computeMarketValue(ask, bid));
				prices.add(price);
			}
			return prices;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
