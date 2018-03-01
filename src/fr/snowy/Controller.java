package fr.snowy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.snowy.api.kraken.KrakenAPI;
import fr.snowy.model.*;
import fr.snowy.model.kraken.*;
import fr.snowy.ui.Frame;

public class Controller {

	private Frame frame;
	private KrakenAPI krakenApi;
	private Wallet wallet;
	private Market market;
	

	public Controller() throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		this.init();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				save();
			}
		});
	}

	public void updateOrders() {
		HashMap<String, String> parameters = new HashMap<>();
		parameters.put("start", String.valueOf(wallet.getLastOrderTimestamp()));
		this.wallet.setOrders(this.krakenApi.queryOrders(parameters));
	}

	public void updatePrices() {
		String krakenFiat = KrakenUtils.convertCurrencyToKraken(Fiat.DEFAULT);
		String krakenCrypto;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> parameters = new HashMap<>();

		for (Crypto crypto : Crypto.values()) {
			krakenCrypto = KrakenUtils.convertCurrencyToKraken(crypto);
			parameters.put("pair", krakenCrypto + krakenFiat);
		}
		this.market.putPrices(this.krakenApi.queryPrices(parameters));
	}

	public void updateBalance() {
		Map<String, String> parameters = new HashMap();

		parameters.put("asset", "ZEUR");
		this.wallet.setBalance(this.krakenApi.queryBalance(parameters));

	}

	public void init() {
		this.krakenApi = new KrakenAPI();
		if ((this.market = (Market) deserialize("market.bloc")) == null)
			this.market = new Market();
		this.wallet = new Wallet();
		this.frame = new Frame(this, this.wallet, this.market);
	}

	public void save() {
		serialize(this.wallet, "wallet.bloc");
	}

	public Object deserialize(String fileName) {
		FileInputStream fs;
		ObjectInputStream is;
		Object object = null;
		try {
			fs = new FileInputStream(fileName);
			is = new ObjectInputStream(fs);
			object = is.readObject();
			is.close();
			fs.close();
		} catch (IOException | ClassNotFoundException e) {
		}
		return object;
	}

	public void serialize(Object object, String filename) {
		FileOutputStream fs;
		ObjectOutputStream os;
		try {
			fs = new FileOutputStream(filename);
			os = new ObjectOutputStream(fs);
			os.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Wallet getWallet() {
		return this.wallet;
	}
	
	
	public static void main(String args[]) {
		try {
			new Controller();
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
