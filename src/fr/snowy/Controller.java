package fr.snowy;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.self.kraken.api.KrakenApi;
import edu.self.kraken.api.KrakenApi.Method;
import fr.snowy.model.*;
import fr.snowy.model.kraken.*;
import fr.snowy.ui.Frame;

public class Controller {
	
	 private static String KEY = "2eLYp6Diwqt3L15lObZiT+/jFoPV6sasVG+14gPJIT35MIbBw2WSfGM5",
	 SECRET = "8xnmLE+ZAvGNQfbKWLScLwdyIUg+ymkj5XAe+gJxIdym/PFtCuWJ4c1DptKQfllOP/URZUidGP9LJBwB3/IYEw==";

	private Frame frame;
	private KrakenApi krakenApi;
	private Wallet wallet;
	private KrakenParser krakenParser;
	private Market market;

	public Controller() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		this.krakenApi = new KrakenApi();
		this.wallet = new Wallet();
		this.market = new Market();
		this.frame = new Frame(this, this.wallet, this.market);

		this.krakenApi.setKey(KEY);
		this.krakenApi.setSecret(SECRET);
//		this.update();
		this.tests();
	}

	public static void main(String args[]) {
		try {
			new Controller();
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		Map<String, String> input = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		String response;
		
		input.put("asset", "ZEUR");
		try {
			response = this.krakenApi.queryPrivate(Method.BALANCE, input);
			System.out.println("response received " + response);
			this.krakenParser = mapper.readValue(response, KrakenParser.class);
			this.wallet.setBalance(KrakenUtils.convertToKrakenWallet(krakenParser));
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		
		input = new HashMap();
		input.put("pair", "XXBTZEUR");
		
		try {
			response = this.krakenApi.queryPrivate(Method.CLOSED_ORDERS);
			System.out.println(response);
			this.krakenParser = mapper.readValue(response, KrakenParser.class);
			this.wallet.setOrders(KrakenUtils.convertToKrakenOrders(krakenParser));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.frame.update();
		
	}
	
	public void tests()
	{
	    //
		String response; 
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> input = new HashMap();
		input.put("pair", "XXBTZEUR");
		
		try {
//		    response = this.krakenApi.queryPublic(Method.TICKER, input);
			response = "{\"error\":[],\"result\":{\"XXBTZEUR\":{\"a\":[\"9124.90000\",\"1\",\"1.000\"],\"b\":[\"9120.00000\",\"4\",\"4.000\"],\"c\":[\"9120.00000\",\"0.15460000\"],\"v\":[\"168.16693995\",\"9985.28473015\"],\"p\":[\"9070.43189\",\"8860.91132\"],\"t\":[737,46440],\"l\":[\"9009.10000\",\"8364.00000\"],\"h\":[\"9124.90000\",\"9124.90000\"],\"o\":\"9017.70000\"}}}";
		    System.out.println(response);
		    this.krakenParser = mapper.readValue(response, KrakenParser.class);
		    this.market.putPrice(KrakenUtils.parseFromKrakenPrice(krakenParser));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		this.frame.update();
	}
	
	public Wallet getWallet()
	{
		return this.wallet;
	}
	
}
