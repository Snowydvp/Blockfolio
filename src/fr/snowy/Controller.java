package fr.snowy;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
	private KrakenParser ordersParseTest;

	public Controller() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		this.wallet = new Wallet();
		this.frame = new Frame(this, this.wallet);
		this.krakenApi = new KrakenApi();

		this.krakenApi.setKey(KEY);
		this.krakenApi.setSecret(SECRET);
		this.update();
//		this.tests();
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
		
		input.put("pair", "XXBTZEUR");
		try {
			
			response = this.krakenApi.queryPrivate(Method.CLOSED_ORDERS);
			this.ordersParseTest = mapper.readValue(response, KrakenParser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.frame.update();
		
	}
	
	public void tests()
	{
		String response; 
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> input = new HashMap();
		
		
		System.out.println(this.ordersParseTest);
		System.out.println(KrakenUtils.convertToKrakenOrders(ordersParseTest));
	}
	
	public Wallet getWallet()
	{
		return this.wallet;
	}
	
}
