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
	HashMap<Currency, Float> balance; 
	
	public BalancePanel(Frame frame, HashMap<Currency, Float> balance)
	{
		this.frame = frame;
		this.balance = balance;
		
		setLayout(new GridLayout(0, 2));
	}

	public void update()
	{
//		removeAll();
		
		Label currencyLbl, valueLbl;
		
		for(Currency currency : balance.keySet())
		{
			currencyLbl = new Label(currency.getName());
			valueLbl = new Label(balance.get(currency).toString() + ' ' + currency.getUnit());
			add(currencyLbl);
			add(valueLbl);
		}
		frame.repaint();
	}
}
