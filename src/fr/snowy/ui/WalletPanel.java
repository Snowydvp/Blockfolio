package fr.snowy.ui;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.HashMap;
import javax.swing.JPanel;
import fr.snowy.model.Currency;
import fr.snowy.model.Wallet;

public class WalletPanel extends JPanel {
	
	public WalletPanel()
	{
		setLayout(new GridLayout(0, 2));
	}

	public void updateWallet(Wallet wallet)
	{
		HashMap<Currency, Float> walletContent = wallet.getWalletContent(); 
		Label currencyLbl, valueLbl;
		
		for(Currency currency : walletContent.keySet())
		{
			currencyLbl = new Label(currency.getName());
			valueLbl = new Label(walletContent.get(currency).toString() + ' ' + currency.getUnit());
			add(currencyLbl);
			add(valueLbl);
		}
	}
}
