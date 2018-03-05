package fr.snowy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;

import fr.snowy.model.Wallet;
import fr.snowy.ui.Frame;

public class Controller {

	private Frame frame;
	private Wallet wallet;
	private Exchange krakenExchange;
	private ExchangeSpecification krakenSpecification;

	public Controller() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		Scanner sc = new Scanner(new File("resources/secret.txt"));
		if ((this.wallet = (Wallet) deserialize("wallet.bloc")) == null)
			this.wallet = new Wallet();
		this.frame = new Frame(this, this.wallet, null);
		this.krakenExchange = new KrakenExchange();
		this.krakenSpecification.setApiKey(sc.nextLine());
		this.krakenSpecification.setSecretKey(sc.nextLine());
		krakenSpecification = krakenExchange.getExchangeSpecification();
	}

	public void updateOrders() {
	}

	public void updatePrices() {
		try {
			krakenExchange.getMarketDataService().getTicker(CurrencyPair.BTC_EUR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBalance() {
		try {
			this.wallet.setBalance(krakenExchange.getAccountService().getAccountInfo().getWallet().getBalances());
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public static void main(String args[]) {
		try {
			new Controller();
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}

}
