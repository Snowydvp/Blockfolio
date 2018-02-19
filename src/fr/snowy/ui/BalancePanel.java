package fr.snowy.ui;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;
import fr.snowy.model.Currency;
import fr.snowy.model.Wallet;

public class BalancePanel extends JPanel {
	
	private Frame frame;
	Wallet wallet;
	
	public BalancePanel(Frame frame, Wallet wallet)
	{
		this.frame = frame;
		this.wallet = wallet;
		
		setLayout(new GridLayout(0, 2));
	}

	public void update()
	{
		removeAll();
		
		Label currencyLbl, valueLbl;
		
		for(Currency currency : wallet.getBalance().keySet())
		{
			currencyLbl = new Label(currency.getName());
			valueLbl = new Label(wallet.getBalance().get(currency).toString() + ' ' + currency.getUnit());
			add(currencyLbl);
			add(valueLbl);
		}
		frame.repaint();
	}
}
