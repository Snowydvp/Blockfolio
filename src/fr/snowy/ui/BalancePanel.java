package fr.snowy.ui;

import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
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
		
		JLabel currencyLbl, valueLbl;
		
		for(Currency currency : wallet.getBalance().keySet())
		{
			currencyLbl = new JLabel(currency.getName());
			valueLbl = new JLabel(String.format("%.3f", wallet.getBalance().get(currency)) + ' ' + currency.getUnit());
			add(currencyLbl);
			add(valueLbl);
		}
		frame.repaint();
	}
}
