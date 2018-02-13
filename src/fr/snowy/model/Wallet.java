package fr.snowy.model;

import java.util.HashMap;

public class Wallet {
	
	private HashMap<Currency, Float> wallet;
	
	public Wallet()
	{
		this.wallet = new HashMap<>();
	}
	

	@Override
	public String toString() {
		return "Wallet [wallet dynamic = " + wallet + "]";
	}
	
	public HashMap<Currency, Float> getWalletContent()
	{
		return wallet;
	}
	
	

}
