package fr.snowy;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.self.kraken.api.KrakenApi;
import edu.self.kraken.api.KrakenApi.Method;
import fr.snowy.model.Wallet;
import fr.snowy.model.WalletParser;
import fr.snowy.ui.Frame;

public class Controller {
	
	 private static String KEY = "2eLYp6Diwqt3L15lObZiT+/jFoPV6sasVG+14gPJIT35MIbBw2WSfGM5",
	 SECRET = "8xnmLE+ZAvGNQfbKWLScLwdyIUg+ymkj5XAe+gJxIdym/PFtCuWJ4c1DptKQfllOP/URZUidGP9LJBwB3/IYEw==";

	private Frame frame;
	private KrakenApi krakenApi;
	private Wallet wallet;
	private WalletParser walletParser;

	public Controller() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		this.frame = new Frame(this);
		this.krakenApi = new KrakenApi();

		this.frame.setVisible(true);
		this.krakenApi.setKey(KEY);
		this.krakenApi.setSecret(SECRET);
		this.refreshWallet();
	}

	public static void main(String args[]) {
		try {
			new Controller();
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshWallet() {
		Map<String, String> input = new HashMap();
		ObjectMapper mapper;
		String response;
		input.put("asset", "ZEUR");
		
		try {
			response = this.krakenApi.queryPrivate(Method.BALANCE, input);
			System.out.println("response received " + response);
			mapper = new ObjectMapper();
			this.walletParser = mapper.readValue(response, WalletParser.class);
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.wallet = this.walletParser.convertToKraken();
		this.frame.getWalletPanel().updateWallet(wallet);
	}
	


}
