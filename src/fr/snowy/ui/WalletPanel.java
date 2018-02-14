package fr.snowy.ui;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.HashMap;
import javax.swing.JPanel;
import fr.snowy.model.Currency;
import fr.snowy.model.Wallet;

public class WalletPanel extends JPanel {
	
	private Frame frame;
	
	public WalletPanel(Frame frame)
	{
		this.frame = frame;
		
		setLayout(new GridLayout(0, 2));
	}

	public void updateWallet(Wallet wallet)
	{
		removeAll();
		HashMap<Currency, Float> walletContent = wallet.getWalletContent(); 
		Label currencyLbl, valueLbl;
		
		for(Currency currency : walletContent.keySet())
		{
			currencyLbl = new Label(currency.getName());
			valueLbl = new Label(walletContent.get(currency).toString() + ' ' + currency.getUnit());
			add(currencyLbl);
			add(valueLbl);
		}
		frame.repaint();
	}
}
