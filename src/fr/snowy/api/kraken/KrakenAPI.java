package fr.snowy.api.kraken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.self.kraken.api.KrakenApi;
import edu.self.kraken.api.KrakenApi.Method;
import fr.snowy.model.Order;
import fr.snowy.model.kraken.KrakenParser;
import fr.snowy.model.kraken.KrakenUtils;

public class KrakenAPI {

	private static String KEY = "2eLYp6Diwqt3L15lObZiT+/jFoPV6sasVG+14gPJIT35MIbBw2WSfGM5",
			SECRET = "8xnmLE+ZAvGNQfbKWLScLwdyIUg+ymkj5XAe+gJxIdym/PFtCuWJ4c1DptKQfllOP/URZUidGP9LJBwB3/IYEw==";
	
	private KrakenApi api;
	private KrakenParser krakenParser;
	
	public KrakenAPI()
	{
		this.api = new KrakenApi();
		this.api.setKey(KEY);
		this.api.setSecret(SECRET);
	}
	
	public ArrayList<Order> queryOrders(HashMap<String, String> parameters) {
		ObjectMapper mapper = new ObjectMapper();
		String response;

		try {
			response = this.api.queryPrivate(Method.CLOSED_ORDERS);
			System.out.println(response);
			this.krakenParser = mapper.readValue(response, KrakenParser.class);
			return KrakenUtils.convertToKrakenOrders(krakenParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
